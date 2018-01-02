package mblog.core.persist.service.impl;


import mblog.base.exception.MtonsException;
import mblog.core.data.AccountProfile;
import mblog.core.data.BadgesCount;
import mblog.core.data.User;
import mblog.core.persist.dao.UserDao;
import mblog.core.persist.entity.PostPO;
import mblog.core.persist.entity.UserPO;
import mblog.core.persist.service.NotifyService;
import mblog.core.persist.service.UserService;

import mblog.core.persist.utils.BeanMapUtils;
import mblog.core.persist.utils.CommonUtils;
import mtons.modules.lang.EntityStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * Created by zhuzhaolin on 2017/10/26.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private NotifyService notifyService;



    @Override
    public AccountProfile login(String username, String password) {

        UserPO po = userDao.findByUsername(username);
        AccountProfile u = null;

        Assert.notNull(po , "账户不存在");

      if (StringUtils.equals(po.getPassword() , CommonUtils.encryptedPassword(password))){
            po.setLastLogin(Calendar.getInstance().getTime());
            u = BeanMapUtils.copyPassport(po);
        }
        BadgesCount badgesCount = new BadgesCount();
        badgesCount.setNotifies(notifyService.unread4Me(u.getId()));

        u.setBadgesCount(badgesCount);
        return u;
    }

    @Override
    public AccountProfile getProfileByName(String username) {
        UserPO po = userDao.findByUsername(username);
        AccountProfile u = null;

        Assert.notNull(po, "账户不存在");

//		Assert.state(po.getStatus() != Const.STATUS_CLOSED, "您的账户已被封禁");
        po.setLastLogin(Calendar.getInstance().getTime());

        u = BeanMapUtils.copyPassport(po);

        BadgesCount badgesCount = new BadgesCount();
        badgesCount.setNotifies(notifyService.unread4Me(u.getId()));

        u.setBadgesCount(badgesCount);

        return u;
    }

    @Override
    public User register(User user) {
        Assert.notNull(user , "Parameter user can not be null!");
        Assert.hasLength(user.getUsername() , "用户名不能为空");
        Assert.hasLength(user.getPassword() , "密码不能为空");

        UserPO check = userDao.findByUsername(user.getUsername());

        Assert.isNull(check , "用户名已经存在");

        if (StringUtils.isNotEmpty(user.getEmail())){
            check = userDao.findByEmail(user.getEmail());
            Assert.isNull(check , "邮箱已经被注册!");
        }

        UserPO po = new UserPO();

        BeanUtils.copyProperties(user , po);

        Date now = Calendar.getInstance().getTime();
        po.setPassword(CommonUtils.encryptedPassword(user.getPassword()));
        po.setStatus(EntityStatus.ENABLED);
        po.setActiveEmail(EntityStatus.ENABLED);
        po.setCreated(now);

        userDao.save(po);
        return BeanMapUtils.copy(po , 0);
    }


    @Override
    public User get(long userid) {
        UserPO po = userDao.findOne(userid);
        User ret = null;
        if (po != null){
            ret = BeanMapUtils.copy(po , 0);
        }
        return ret;
    }

    @Override
    public Map<Long, User> findMapByIds(Set<Long> ids) {
        if (ObjectUtils.isEmpty(ids)){
            return Collections.emptyMap();
        }
        List<UserPO> list = userDao.findAll(new Specification<UserPO>() {
            @Override
            public Predicate toPredicate(Root<UserPO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Long> id = root.get("id");
                criteriaQuery.where(id.in(ids));
                return null;
            }
        });
        Map<Long , User> ret = new HashMap<>();
        list.forEach(po -> {
            ret.put(po.getId() , BeanMapUtils.copy(po , 0));
        });
        return ret;
    }

    @Override
    public AccountProfile update(User user) {
        userDao.updateUser(user.getName() , user.getSignature() , user.getId());
        UserPO po = userDao.getOne(user.getId());
        if (null != po){
            po.setName(user.getName());
            po.setSignature(user.getSignature());
        }
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    public void updatePassword(long id, String oldPassword, String newPassword) {
        Assert.hasLength(newPassword , "密码不能为空");

        UserPO po = userDao.findOne(id);

        if (po != null){
            Assert.isTrue(CommonUtils.encryptedPassword(oldPassword).equals(po.getPassword()) , "当前密码不正确");
            po.setPassword(CommonUtils.encryptedPassword(newPassword));
        }
        userDao.updatePassword(CommonUtils.encryptedPassword(newPassword) , id);
    }

    @Override
    public AccountProfile updateEmail(long id, String email) {
        UserPO po= userDao.getOne(id);
        if (null != po){
            UserPO check = userDao.findByEmail(email);
            if (check != null && check.getId() != po.getId()){
               throw new MtonsException("该邮箱地址已经被使用了");
            }
            //userDao.updateEmail(email , id);
            po.setEmail(email);
            po.setActiveEmail(0);
        }
        return BeanMapUtils.copyPassport(po);
    }

    @Transactional
    @Override
    public AccountProfile updateActiveEmail(long id, int activeEmail) {
        UserPO po = userDao.findOne(id);

        if (!ObjectUtils.isEmpty(po)){
            po.setActiveEmail(activeEmail);
            //莫名其妙明明activeEmail值已经改变却不会自动更新，只好显示更新，却打印出两条update语句
            userDao.updateEmail(1 , 2);
        }

        return BeanMapUtils.copyPassport(po);
    }

    @Override
    public AccountProfile updateAvatar(long id, String path) {
        UserPO po = userDao.findOne(id);
        if (po != null){
            userDao.updateAvatar(path , id);
            po.setAvatar(path);
        }
        return BeanMapUtils.copyPassport(po);
    }


}

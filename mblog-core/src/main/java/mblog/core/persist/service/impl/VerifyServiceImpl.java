package mblog.core.persist.service.impl;

import mblog.base.exception.MtonsException;
import mblog.base.lang.Consts;
import mblog.core.persist.dao.VerifyDao;
import mblog.core.persist.entity.VerifyPO;
import mblog.core.persist.service.VerifyService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Date;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/5 23:11.
 */
@Transactional
@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    VerifyDao verifyDao;

    /**
     * 验证码存在时间 单位:分钟
     */
    private static final int  survivalTime = 30;

    @Override
    public String generateCode(long userId, int type, String target) {

        VerifyPO po = verifyDao.findByUserId(userId);

        String code = RandomStringUtils.randomNumeric(10);
        Date now  = new Date();

        if (po == null){
            po = new VerifyPO();
            po.setUserId(userId);
            po.setCreated(now);
            po.setExpired(DateUtils.addMinutes(now , survivalTime));
            po.setCode(code);
            po.setType(type);
            po.setTarget(target);
            verifyDao.save(po);
        }else {
            long interval = ( now.getTime() - po.getCreated().getTime() ) / 1000;

            if (interval <= 60){
                throw new MtonsException("发送时间不能少于1分钟");
            }
            //把验证位置 0
            po.setStatus(0);
            po.setCreated(now);
            po.setExpired(DateUtils.addMinutes(now , survivalTime));
            po.setCode(code);
            po.setType(type);
            po.setTarget(target);
        }
        return code;

    }

    @Override
    public String verify(long userId, int type, String code) {
        Assert.hasLength(code , "验证码不能为空");
        VerifyPO po = verifyDao.findOne(new Specification<VerifyPO>() {
            @Override
            public Predicate toPredicate(Root<VerifyPO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                Path<Integer> userIdPath = root.get("userId");
                Path<Integer> typePath = root.get("type");
                criteriaQuery.where( cb.equal(userIdPath , userId) ,cb.equal(typePath ,type));
                return null;
            }
        });
        Assert.notNull(po , "你没有进行过类型验证");
        Date now  = new Date();
        org.springframework.util.Assert.state(now.getTime() <= po.getExpired().getTime(), "验证码已过期");

        org.springframework.util.Assert.isTrue(po.getStatus() == Consts.VERIFY_STATUS_INIT, "验证码已经使用过");

        org.springframework.util.Assert.state(code.equals(po.getCode()), "验证码不对");

        String token = RandomStringUtils.randomNumeric(8);
        po.setToken(token);

        po.setStatus(Consts.VERIFY_STATUS_TOKEN);

        return token;
    }
}

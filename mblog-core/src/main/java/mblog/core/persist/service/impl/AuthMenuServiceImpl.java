package mblog.core.persist.service.impl;

import mblog.core.data.AuthMenu;
import mblog.core.persist.dao.AuthMenuDao;
import mblog.core.persist.entity.AuthMenuPO;
import mblog.core.persist.service.AuthMenuService;
import mblog.core.persist.utils.BeanMapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/4 21:57.
 */
@Service
@Transactional
public class AuthMenuServiceImpl implements AuthMenuService {

    @Autowired
    private AuthMenuDao authMenuDao;

    @Override
    public List<AuthMenu> findByParentId(long parentId) {
        List<AuthMenu> authMenus = new ArrayList<>();
        List<AuthMenuPO> authMenuPOS = authMenuDao.findAllByParentIdOrderBySortAsc(parentId);
        if (authMenuPOS != null){
            for (AuthMenuPO po : authMenuPOS){
                AuthMenu authMenu = BeanMapUtils.copy(po);
                authMenus.add(authMenu);
            }
        }
        return authMenus;
    }

    @Override
    public List<AuthMenu> listAll() {
        List<AuthMenuPO> list = authMenuDao.findAll();
        List<AuthMenu> rets = new ArrayList<>();
        list.forEach(po ->{
            AuthMenu a = new AuthMenu();
            BeanUtils.copyProperties(po , a , "parent", "roles", "children");
            rets.add(a);
        });
        return rets;
    }
}

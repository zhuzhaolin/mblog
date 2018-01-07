package mblog.core.persist.service.impl;

import mblog.core.data.Role;
import mblog.core.persist.dao.RoleDao;
import mblog.core.persist.entity.RolePO;
import mblog.core.persist.service.RoleService;
import mblog.core.persist.utils.BeanMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 22:35.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Role> paging(Pageable pageable) {
        Page<RolePO> page = roleDao.findAllByOrderByIdDesc(pageable);
        List<Role> roles = new ArrayList<>();
        page.getContent().forEach(po -> {
            roles.add(BeanMapUtils.copy(po));
        });
        return new PageImpl<Role>(roles , pageable , page.getTotalElements());
    }
}

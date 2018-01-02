package mblog.core.persist.service.impl;

import mblog.base.lang.Consts;
import mblog.core.data.Group;
import mblog.core.persist.dao.GroupDao;
import mblog.core.persist.entity.GroupPO;
import mblog.core.persist.service.GroupService;
import mblog.core.persist.utils.BeanMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/1 11:57.
 */
@Service
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;
    @Override
    public List<Group> findAll(int status) {
        List<GroupPO> list;
        if (status > Consts.IGNORE){
            list = groupDao.findAllByStatus(status);
        }else {
            list = groupDao.findAll();
        }
        List<Group> rets = new ArrayList<>();
        list.forEach(groupPO -> rets.add(BeanMapUtils.copy(groupPO)));
        return rets;
    }
}

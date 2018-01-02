package mblog.core.persist.service.impl;

import mblog.base.lang.Consts;
import mblog.core.persist.dao.TagDao;
import mblog.core.persist.entity.TagPO;
import mblog.core.persist.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/30 10:16.
 */
@Service
@Transactional
public class TagServiceImpl implements TagService{

    @Autowired
    TagDao tagDao;

    @Override
    public void indentityHots(String name) {
        TagPO po = tagDao.findByName(name);
        if (po != null){
            po.setHots(po.getHots() + Consts.IDENTITY_STEP);
        }
    }
}

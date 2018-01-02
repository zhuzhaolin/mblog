package mblog.core.persist.dao.impl;

import mblog.base.lang.Consts;
import mblog.base.utils.NumberUtils;
import mblog.core.persist.dao.NotifyDao;
import mblog.core.persist.entity.NotifyPO;
import mtons.modules.annotation.Repository;
import mtons.modules.persist.impl.BaseRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
@Repository(entity = NotifyPO.class)
@Transactional
public class NotifyDaoImpl extends BaseRepositoryImpl<NotifyPO> implements NotifyDao{


    @Override
    public int unread4Me(long ownId) {
        Criteria count = createCriteria();
        count.setProjection(Projections.rowCount());
        count.add(Restrictions.eq("ownId" , ownId));
        count.add(Restrictions.eq("status" , Consts.UNREAD));
        return NumberUtils.changeToInt(count.uniqueResult());
    }
}

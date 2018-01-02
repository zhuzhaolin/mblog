package mblog.core.persist.service.impl;

import mblog.core.persist.service.NotifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
@Service
public class NotifyServiceImpl implements NotifyService{


    @Override
    @Transactional(readOnly = true)
    public int unread4Me(long ownId) {
        return 5;
    }
}

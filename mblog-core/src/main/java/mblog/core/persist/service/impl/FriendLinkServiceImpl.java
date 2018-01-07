package mblog.core.persist.service.impl;

import mblog.core.data.FriendLink;
import mblog.core.persist.dao.FriendLinkDao;
import mblog.core.persist.entity.FriendLinkPO;
import mblog.core.persist.service.FriendLinkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/7 0:07.
 */
@Service
@Transactional
public class FriendLinkServiceImpl implements FriendLinkService{

    @Autowired
    private FriendLinkDao friendLinkDao;
    @Override
    public List<FriendLink> findAll() {
        List<FriendLinkPO> linkPOs = friendLinkDao.findAll();

        List<FriendLink> rets = new ArrayList<>();
        for (FriendLinkPO po : linkPOs){
            FriendLink m = new FriendLink();
            BeanUtils.copyProperties(po , m);
            rets.add(m);
        }
        return rets;
    }
}

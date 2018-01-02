package mblog.core.persist.service.impl;

import mblog.base.lang.Consts;
import mblog.core.data.Feeds;
import mblog.core.data.Post;
import mblog.core.persist.dao.FeedsDao;
import mblog.core.persist.entity.FeedsPO;
import mblog.core.persist.service.FeedsService;
import mblog.core.persist.service.PostService;
import mblog.core.persist.utils.BeanMapUtils;
import mtons.modules.pojos.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created by zhuzhaolin on 2017/11/21.
 */
@Service
public class FeedsServiceImpl implements FeedsService {

    @Autowired
    FeedsDao feedsDao;

    @Autowired
    PostService postService;

    @Override
    public Page<Feeds> findUserFeeds(Pageable pageable, long ownId) {
       Page<FeedsPO> page = feedsDao.findAllByOwnIdOrderByIdDesc(pageable , ownId);

       List<Feeds> rets = new ArrayList<>();

       Set<Long> postIds = new HashSet<>();

       for (FeedsPO po : page.getContent()){
           Feeds f = BeanMapUtils.copy(po);
           rets.add(f);

           postIds.add(f.getPostId());
       }
       //加载文章
        Map<Long , Post> postMap = postService.findMultimeMapByIds(postIds);

        for (Feeds f : rets){
            f.setPost(postMap.get(f.getPostId()));
        }

        return new PageImpl<>(rets , pageable , page.getTotalElements());
    }

    @Override
    public List<Feeds> findConcreteContentByIds(List<FeedsPO> feedsPOS) {
        List<Feeds> rets = new ArrayList<>();
        Set<Long> postIds = new HashSet<>();
        for (FeedsPO po : feedsPOS){
            Feeds feeds = BeanMapUtils.copy(po);
            rets.add(feeds);

            postIds.add(feeds.getPostId());
        }

        //加载文章
        Map<Long , Post> postMap = postService.findMultimeMapByIds(postIds);

        for (Feeds f : rets){
            f.setPost(postMap.get(f.getPostId()));
        }
        return rets;
    }
}

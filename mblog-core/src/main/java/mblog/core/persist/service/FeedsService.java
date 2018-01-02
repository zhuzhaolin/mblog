package mblog.core.persist.service;

import mblog.core.data.Feeds;
import mblog.core.persist.entity.FeedsPO;
import mblog.core.persist.entity.UserPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhuzhaolin on 2017/11/20.
 */
public interface FeedsService {

    Page<Feeds> findUserFeeds(Pageable pageable, long ownId);

    List<Feeds> findConcreteContentByIds(List<FeedsPO> feedsPOS);
}

package mblog.core.persist.dao;

import mblog.core.persist.entity.FeedsPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by zhuzhaolin on 2017/11/21.
 */
public interface FeedsDao extends JpaSpecificationExecutor<FeedsPO> , CrudRepository<FeedsPO , Integer> {
    Page<FeedsPO> findAllByOwnIdOrderByIdDesc(Pageable pageable , long ownId);
}

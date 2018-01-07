package mblog.core.persist.dao;

import mblog.core.persist.entity.CommentPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 18:19.
 */
public interface CommentDao extends JpaRepository<CommentPO , Long> , JpaSpecificationExecutor<CommentPO> {
}

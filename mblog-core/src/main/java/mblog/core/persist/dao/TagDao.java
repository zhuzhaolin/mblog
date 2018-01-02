package mblog.core.persist.dao;

import mblog.core.persist.entity.TagPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/30 10:17.
 */
public interface TagDao extends JpaRepository<TagPO , Long>{
    TagPO findByName(String name);
}

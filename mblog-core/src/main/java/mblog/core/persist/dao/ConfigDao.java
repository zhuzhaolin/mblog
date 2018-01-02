package mblog.core.persist.dao;

import mblog.core.persist.entity.ConfigPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/17 19:45.
 */
public interface ConfigDao extends JpaRepository<ConfigPO , Long> {
}

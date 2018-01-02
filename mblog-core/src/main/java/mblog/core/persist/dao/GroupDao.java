package mblog.core.persist.dao;

import mblog.core.persist.entity.GroupPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/1 11:58.
 */
public interface GroupDao extends JpaRepository<GroupPO , Integer> , JpaSpecificationExecutor<GroupPO>{
    List<GroupPO> findAllByStatus(int status);
}

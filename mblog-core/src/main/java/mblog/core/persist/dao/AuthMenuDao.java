package mblog.core.persist.dao;

import mblog.core.persist.entity.AuthMenuPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/4 21:58.
 */
public interface AuthMenuDao extends JpaRepository<AuthMenuPO , Long> , JpaSpecificationExecutor<AuthMenuPO> {
    List<AuthMenuPO> findAllByParentIdOrderBySortAsc(long parentId);
}

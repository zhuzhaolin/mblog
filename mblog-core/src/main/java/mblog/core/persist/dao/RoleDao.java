package mblog.core.persist.dao;

import mblog.core.persist.entity.RolePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 22:35.
 */
public interface RoleDao extends JpaRepository<RolePO , Long>,JpaSpecificationExecutor<RolePO> {
    Page<RolePO> findAllByOrderByIdDesc(Pageable pageable);
}

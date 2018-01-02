package mblog.core.persist.dao;

import mblog.core.persist.entity.AttachPO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

/**
 * Created by zhuzhaolin on 2017/11/25.
 */
public interface AttachDao extends JpaSpecificationExecutor<AttachPO>,JpaRepository<AttachPO ,Long> {

    List<AttachPO> findById(Specification<AttachPO> toIds);
}

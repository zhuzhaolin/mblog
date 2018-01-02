package mblog.core.persist.dao;

import mblog.core.persist.dao.custom.PostDaoCustom;
import mblog.core.persist.entity.PostPO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by zhuzhaolin on 2017/11/24.
 */
public  interface PostDao extends JpaSpecificationExecutor<PostPO> ,CrudRepository<PostPO , Long> , PostDaoCustom {

    List<PostPO> findById(Specification<PostPO> var1);
    List<PostPO> findById(Collection<Long> ids);
}

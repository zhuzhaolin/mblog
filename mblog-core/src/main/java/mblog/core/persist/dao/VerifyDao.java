package mblog.core.persist.dao;

import mblog.core.persist.entity.VerifyPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/5 23:13.
 */
public interface VerifyDao extends JpaRepository<VerifyPO , Long> ,JpaSpecificationExecutor<VerifyPO> {

    VerifyPO findByUserId(long userId);
}

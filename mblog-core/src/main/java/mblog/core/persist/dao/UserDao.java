package mblog.core.persist.dao;

import mblog.core.persist.entity.UserPO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhuzhaolin on 2017/10/28.
 */
public interface UserDao extends JpaSpecificationExecutor<UserPO>,JpaRepository<UserPO , Long>{

    public UserPO findByUsername(String username);

    public List<UserPO> findById(Specification<UserPO> var1);

    @Transactional
    @Modifying
    @Query(value = "UPDATE mto_users SET name = ? , signature = ? WHERE id = ?" , nativeQuery = true)
    public void updateUser(String name , String signature , long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE mto_users SET password = ?  WHERE id = ?" , nativeQuery = true)
    public void updatePassword(String password , long id);

    public UserPO findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE mto_users SET active_email = ?  WHERE id = ?" , nativeQuery = true)
    public void updateEmail(int active_email , long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE mto_users SET avatar = ?  WHERE id = ?" , nativeQuery = true)
    public void updateAvatar(String avatar , long id);
}

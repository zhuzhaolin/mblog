package mblog.core.persist.service;

import mblog.core.data.AccountProfile;
import mblog.core.data.AuthMenu;
import mblog.core.data.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sun.security.util.Password;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhuzhaolin on 2017/10/26.
 */
public interface UserService {

    /**
     * 登录
     */
    public AccountProfile login(String username, String password);

    AccountProfile getProfileByName(String username);


    /**
     * 注册
     * @param user
     * @return
     */
    User register(User user);

    /**
     * 查询单个用户信息
     */
    public User get(long id);

    Map<Long, User> findMapByIds(Set<Long> ids);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    AccountProfile update(User user);

    /**
     * 修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(long id, String oldPassword, String newPassword);

    /**
     * 修改邮箱
     *
     * @param id
     * @param email
     * @return
     */
    AccountProfile updateEmail(long id, String email);

    AccountProfile updateActiveEmail(long id, int activeEmail);

    User getByUsername(String username);

    /**
     * 修改头像
     *
     * @param id
     * @param path
     * @return
     */
    AccountProfile updateAvatar(long id, String path);

    Page<User> paging(Pageable pageable);

    List<AuthMenu> getMenuList(long id);
}

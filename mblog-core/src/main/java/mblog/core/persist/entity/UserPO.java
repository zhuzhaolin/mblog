package mblog.core.persist.entity;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuzhaolin on 2017/10/28.
 */
@Entity
@Table(name = "mto_users")
public class UserPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 用户名
     */
    @Column(name = "username" , unique = true , length = 64)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password" , length = 64)
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    @Column(name = "name" , length = 18)
    private String name;

    /**
     * 性别
     */
    private int gender;

    /**
     * 邮箱
     */
    @Column(name = "email" , unique = true , length = 128)
    private String email ;

    /**
     * 手机号
     */
    @Column(name = "mobile" , length = 11)
    private String mobile;

    /**
     * 文章数
     */
    private int posts;

    /**
     * 发布评论数
     */
    private int comments;

    /**
     * 关注人数
     */
    private int follows;

    /**
     * 粉丝数
     */
    private int fans;

    /**
     * 收到的喜欢数
     */
    private int favors;

    /**
     * 注册时间
     */
    private Date created;

    /**
     * 注册来源，主要区别第三方登录
     */
    private int source;

    @Column(name = "last_login")
    private Date lastLogin;

    /**
     * 个性签名
     */
    private String signature;

    @ManyToMany(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
    @JoinTable(name = "mto_user_role" , joinColumns = {@JoinColumn(name = "user_id")}  , inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<RolePO> roles = new ArrayList<>();

    /**
     * 邮箱激活状态
     */
    @Column(name = "active_email")
    private int activeEmail;

    /**
     * 用户状态
     */
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getFavors() {
        return favors;
    }

    public void setFavors(int favors) {
        this.favors = favors;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public List<RolePO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolePO> roles) {
        this.roles = roles;
    }

    public int getActiveEmail() {
        return activeEmail;
    }

    public void setActiveEmail(int activeEmail) {
        this.activeEmail = activeEmail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

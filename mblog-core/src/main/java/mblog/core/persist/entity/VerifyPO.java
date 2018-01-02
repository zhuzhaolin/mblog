package mblog.core.persist.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/5 23:14.
 */
@Entity
@Table(name = "mto_verify")
public class VerifyPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 用户Id
     */
    @Column(name = "user_id" , unique = true)
    private long userId;

    /**
     * 验证码
     */
    @Column(length = 60 , nullable = false)
    private String code;

    /**
     * 目标:邮箱
     */
    @Column(length = 96)
    private String target;

    /**
     * 验证类型:注册验证、找回密码验证
     */
    @Column
    private int type;

    /**
     * 过期时间
     */
    @Column(name = "expired" , nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date expired;

    /**
     * 创建时间
     */
    @Column(name = "created" , nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    private String token;

    /**
     * 状态:正常、关闭
     */
    @Column
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

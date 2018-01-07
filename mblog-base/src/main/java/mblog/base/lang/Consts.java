package mblog.base.lang;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
public interface Consts {

    /**
     * 默认头像
     */
    String AVATAR = "/assets/images/ava/default.png";


    /**
     * 分隔符
     */
    String SEPARATOR = ",";

    /**
     * 自动不进
     */
    int IDENTITY_STEP = 1;

    /**
     * 最小时间单位 ， 1秒
     */
    int TIME_MIN = 1000;

    /**
     * 忽略值
     */
    int IGNORE = -1;

    int ZERO = 0;

    /**
     * 初始-初始化
     */
    int STATUS_NORMAL = 0;

    /**
     * 未读
     */
    int UNREAD = 0;

    int VERIFY_BIND = 1;   // bind email

    int VERIFY_STATUS_INIT = 0;      // 验证码-初始
    int VERIFY_STATUS_TOKEN = 1;     // 验证码-已生成token
    int VERIFY_STATUS_CERTIFIED = 2; // 验证码-已使用

    int ACTIVE_EMAIL = 1; // 邮箱激活

    String EMAIL_TEMPLATE_BIND ="emailVerify";
}

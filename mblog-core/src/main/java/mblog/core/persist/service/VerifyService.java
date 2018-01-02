package mblog.core.persist.service;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/5 23:10.
 */
public interface VerifyService {

    /**
     * s生成验证码
     * @param userId
     * @param type
     * @param target
     * @return
     */
    String generateCode(long userId , int type , String target);

    /**
     * 检验验证码有效性
     * @param userId
     * @param code
     * @return token
     */
    String verify(long userId , int type , String code);
}

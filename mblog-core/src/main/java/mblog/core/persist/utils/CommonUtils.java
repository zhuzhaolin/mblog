package mblog.core.persist.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/2 23:59.
 */
public class CommonUtils {

    public static String encryptedPassword(String password){
        return new SimpleHash("MD5" , password , null , 1024).toString();

    }

    public static String encryptedLinkPara(long userId , String verificationCode , int type){
        return new SimpleHash("MD5" , type+userId + verificationCode , null , 1024).toString();
    }

}

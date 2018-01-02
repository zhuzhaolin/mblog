package mblog.shiro.realm;




import mblog.core.data.AccountProfile;
import mblog.core.persist.entity.UserPO;
import mblog.core.persist.service.UserService;
import mblog.core.persist.service.impl.UserServiceImpl;
import mblog.shiro.authc.AccountAuthenticationInfo;
import mtons.modules.lang.Const;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhuzhaolin on 2017/10/9.
 */

public class AccountRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        AccountProfile profile = getAccount(userService , upToken);
        if (profile.getStatus() == Const.STATUS_CLOSED){
            throw new LockedAccountException(profile.getName());
        }

        AccountAuthenticationInfo info = new AccountAuthenticationInfo(token.getPrincipal(), profile.getPassword(), getName());
        info.setProfile(profile);

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
         int i = 1+1;
        return null;
    }

 /*  protected AccountProfile getAccount(UserService userService , AuthenticationToken token){
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        return userService.login(upToken.getUsername() ,String.valueOf(upToken.getPassword()));
    }*/

 public AccountProfile getAccount(UserService userService , AuthenticationToken token){
     UsernamePasswordToken upToken = (UsernamePasswordToken) token;
     return userService.login(upToken.getUsername() ,String.valueOf(upToken.getPassword()));
 }

    public static void main(String[] args) {
        Object result = new SimpleHash("MD5" , "123456" , null , 1024);
        System.out.println(result);
    }
}

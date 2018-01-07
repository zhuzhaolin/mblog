package mblog.shiro.realm;




import mblog.core.data.AccountProfile;
import mblog.core.data.AuthMenu;
import mblog.core.data.User;
import mblog.core.persist.entity.UserPO;
import mblog.core.persist.service.UserService;
import mblog.core.persist.service.impl.UserServiceImpl;
import mblog.shiro.authc.AccountAuthenticationInfo;
import mtons.modules.lang.Const;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
         String username = (String) principals.fromRealm(getName()).iterator().next();
         if (username != null){
             User user = userService.getByUsername(username);
             if (user != null){
                 SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                 List<AuthMenu> menuList = userService.getMenuList(user.getId());
                 for (AuthMenu menu : menuList){
                     if (StringUtils.isNotEmpty(menu.getPermission())){
                         //添加基於permission的權限信息
                         for (String permission : StringUtils.split(menu.getPermission(),",")){
                             info.addStringPermission(permission);
                         }

                     }
                 }
                 return info;
             }
         }
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

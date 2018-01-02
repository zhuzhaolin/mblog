package mblog.shiro.authc;

import mblog.core.data.AccountProfile;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
public class AccountAuthenticationInfo extends SimpleAuthenticationInfo{
    private AccountProfile profile;

    public AccountAuthenticationInfo(){
    }

    public AccountAuthenticationInfo(Object principal, Object credentialsSalt, String realmName) {
        super(principal, credentialsSalt, realmName);
    }

    public AccountProfile getProfile() {
        return profile;
    }

    public void setProfile(AccountProfile profile) {
        this.profile = profile;
    }
}

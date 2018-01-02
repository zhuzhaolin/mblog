package mblog.shiro.authc;

import mblog.core.data.AccountProfile;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by zhuzhaolin on 2017/11/20.
 */
public class AccountSubject extends WebDelegatingSubject{

    private AccountProfile profile;



    public AccountSubject(PrincipalCollection principals, boolean authenticated, String host, Session session,
                          boolean sessionEnabled, ServletRequest request, ServletResponse response, SecurityManager securityManager , AccountProfile profile) {
        super(principals, authenticated, host, session, sessionEnabled, request, response, securityManager);
        this.profile = profile;
    }

    public AccountProfile getProfile() {
        return profile;
    }

    public void setProfile(AccountProfile profile) {
        this.profile = profile;
    }
}

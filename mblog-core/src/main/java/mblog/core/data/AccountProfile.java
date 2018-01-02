package mblog.core.data;

import mtons.modules.pojos.UserProfile;

import java.util.List;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
public class AccountProfile extends UserProfile{

    private static final long serialVersionUID = -8862020000226489384L;
    private int roleId;
    private int activeEmail;
    private String password;

    private List<AuthMenu> authMenus;

    private BadgesCount badgesCount;

    public AccountProfile(long id, String username) {
        super(id, username);
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getActiveEmail() {
        return activeEmail;
    }

    public void setActiveEmail(int activeEmail) {
        this.activeEmail = activeEmail;
    }

    public List<AuthMenu> getAuthMenus() {
        return authMenus;
    }

    public void setAuthMenus(List<AuthMenu> authMenus) {
        for (int i = 0; i < authMenus.size(); i++) {
            for (int j = authMenus.size() - 1 ; j > 0 ; j--){
                if (authMenus.get(i).getSort() > authMenus.get(j).getSort()){
                    AuthMenu temp = authMenus.get(i);
                    authMenus.set(i, authMenus.get(j));
                    authMenus.set(j, temp);
                }
            }

        }
        this.authMenus = authMenus;
    }

    public BadgesCount getBadgesCount() {
        return badgesCount;
    }

    public void setBadgesCount(BadgesCount badgesCount) {
        this.badgesCount = badgesCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

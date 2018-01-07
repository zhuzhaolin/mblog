package mblog.core.persist.utils;

import mblog.core.data.*;
import mblog.core.persist.entity.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by zhuzhaolin on 2017/10/12.
 */
public class BeanMapUtils {


    public static String[] USER_IGNORE = new String[]{"password", "extend", "roles"};

    public static String[] POST_IGNORE_LIST = new String[]{"markdown", "content"};

    public static User copy(UserPO po , int level){
        if (po == null){
            return null;
        }

        User ret = new User();
        BeanUtils.copyProperties(po , ret , USER_IGNORE);

        if (level > 0){
            List<RolePO> rolePOS = po.getRoles();
            List<Role> roles = new ArrayList<>();
            for (RolePO rolePO : rolePOS){
                Role role = copy(rolePO);
                roles.add(role);
            }
            ret.setRoles(roles);
        }
        return ret;
    }

    public static AuthMenu copy(AuthMenuPO po , String ... ignore){
        AuthMenu am = new AuthMenu();
        BeanUtils.copyProperties(po, am, "children");
        return am;

    }

    public static AccountProfile copyPassport(UserPO po){
      AccountProfile passport = new AccountProfile(po.getId() , po.getUsername());
        passport.setId(po.getId());
        passport.setName(po.getName());
        passport.setEmail(po.getEmail());
        passport.setAvatar(po.getAvatar());
        passport.setLastLogin(po.getLastLogin());
        passport.setStatus(po.getStatus());
        passport.setActiveEmail(po.getActiveEmail());
        passport.setPassword(po.getPassword());
        if (SecurityUtils.getSubject().getSession().getAttribute("profile") != null){
            passport.setBadgesCount(((AccountProfile)SecurityUtils.getSubject().getSession().getAttribute("profile")) .getBadgesCount());
        }

        List<AuthMenu> menus = new ArrayList<>();
        List<RolePO> rolePOS = po.getRoles();
        List<Role> roles = new ArrayList<>();
        for (RolePO rolePO : rolePOS){
            Role role = copy(rolePO);
            roles.add(role);
        }
        for (Role role : roles){
            List<AuthMenu> authMenus = role.getAuthMenus();
            menus.addAll(authMenus);
        }
        passport.setAuthMenus(menus);
        return passport;
    }

    public static Role copy(RolePO po) {
        Role r = new Role();
        BeanUtils.copyProperties(po, r, "users", "authMenus");
        List<AuthMenu> authMenus = new ArrayList<>();
        for (AuthMenuPO authMenuPO : po.getAuthMenus()) {
            AuthMenu authMenu = new AuthMenu();
            BeanUtils.copyProperties(authMenuPO, authMenu, "roles", "children");
            authMenus.add(authMenu);
        }
        r.setAuthMenus(authMenus);
        return r;
    }

    public static Comment copy(CommentPO po){
        Comment ret = new Comment();
        BeanUtils.copyProperties(po , ret);
        return ret;
    }

    public static Post copy(PostPO postPO , int level){
        Post d = new Post();
        if (level > 0){
            BeanUtils.copyProperties(postPO , d);
        }else {
            BeanUtils.copyProperties(postPO , d , POST_IGNORE_LIST);
        }
        return d;
    }

    public static Attach copy(AttachPO po){
        Attach ret = new Attach();
        BeanUtils.copyProperties(po , ret);
        return ret;
    }

    public static Group copy(GroupPO po){
        Group r = new Group();
        BeanUtils.copyProperties(po , r);
        return r;
    }

    public static Feeds copy(FeedsPO po){
        Feeds ret = new Feeds();
        BeanUtils.copyProperties(po , ret);
        return ret;
    }
}

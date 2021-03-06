package mblog.web.controller.admin;

import mblog.core.data.AuthMenu;
import mblog.core.data.User;
import mblog.core.persist.service.AuthMenuService;
import mblog.core.persist.service.UserService;
import mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 11:14.
 */
@Controller
@RequestMapping("/admin/users")
public class UsersController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthMenuService authMenuService;

    @RequestMapping("/list")
    public String list(Model model){
        Pageable pageable = wrapPageable();
        Page<User> page = userService.paging(pageable);
        model.addAttribute("page" , page);
        return "/admin/users/list";
    }

    @ModelAttribute
    public void getAuthMenus(Model model){
        List<AuthMenu> authMenus = authMenuService.findByParentId(2);
        model.addAttribute("results" , authMenus);
    }
}

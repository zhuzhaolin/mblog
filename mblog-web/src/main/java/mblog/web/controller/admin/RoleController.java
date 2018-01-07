package mblog.web.controller.admin;

import mblog.core.data.AuthMenu;
import mblog.core.data.Role;
import mblog.core.persist.service.AuthMenuService;
import mblog.core.persist.service.RoleService;
import mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 22:30.
 */
@Controller
@RequestMapping("/admin/roles")
public class RoleController extends BaseController {
    @Autowired
    private AuthMenuService authMenuService;

    @Autowired
    private RoleService roleService;

    @GetMapping("list")
    public String list(Model model){
        Pageable pageable = wrapPageable();
        Page<Role> page = roleService.paging(pageable);
        model.addAttribute("page" , page);
        return "/admin/roles/list";
    }

    @ModelAttribute
    public void getAuthMenus(Model model){
        List<AuthMenu> authMenus = authMenuService.findByParentId(2);
        model.addAttribute("results" , authMenus);
    }

}

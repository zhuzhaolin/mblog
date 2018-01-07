package mblog.web.controller.admin;

import mblog.core.data.AuthMenu;
import mblog.core.persist.service.AuthMenuService;
import mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 23:08.
 */
@Controller
@RequestMapping("/admin/authMenus")
public class AuthMenuController extends BaseController {

    @Autowired
    private AuthMenuService authMenuService;

    @RequestMapping("/list")
    public String list(Model model){
        List<AuthMenu> list = authMenuService.listAll();
        model.addAttribute("list" , list);
        return "/admin/authMenus/list";
    }

    @ModelAttribute
    public void getAuthMenus(Model model){
        List<AuthMenu> authMenus = authMenuService.findByParentId(2);
        model.addAttribute("results" , authMenus);
    }
}

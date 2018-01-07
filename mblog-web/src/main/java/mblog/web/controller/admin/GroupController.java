package mblog.web.controller.admin;

import mblog.base.lang.Consts;
import mblog.core.data.AuthMenu;
import mblog.core.persist.service.AuthMenuService;
import mblog.core.persist.service.GroupService;
import mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 17:59.
 */
@Controller
@RequestMapping("/admin/group")
public class GroupController extends BaseController{

    @Autowired
    private GroupService groupService;

    @Autowired
    private AuthMenuService authMenuService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("list" , groupService.findAll(Consts.IGNORE));
        return "/admin/group/list";
    }

    @ModelAttribute
    public void getAuthMenus(Model model){
        List<AuthMenu> authMenus = authMenuService.findByParentId(2);
        model.addAttribute("results" , authMenus);
    }
}

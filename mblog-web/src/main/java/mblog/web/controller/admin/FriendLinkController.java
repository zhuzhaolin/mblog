package mblog.web.controller.admin;

import mblog.core.data.AuthMenu;
import mblog.core.data.FriendLink;
import mblog.core.persist.service.AuthMenuService;
import mblog.core.persist.service.FriendLinkService;
import mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/7 0:02.
 */
@Controller
@RequestMapping("/admin/friendLink")
public class FriendLinkController extends BaseController {

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private AuthMenuService authMenuService;

    @RequestMapping("/list")
    public String list(Model model){
        List<FriendLink> list = friendLinkService.findAll();
        model.addAttribute("list" , list);
        return "/admin/friendLink/list";
    }


    @ModelAttribute
    public void getAuthMenus(Model model){
        List<AuthMenu> authMenus = authMenuService.findByParentId(2);
        model.addAttribute("results" , authMenus);
    }
}

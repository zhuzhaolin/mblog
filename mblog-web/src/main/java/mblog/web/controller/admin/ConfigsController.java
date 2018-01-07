package mblog.web.controller.admin;

import mblog.core.data.AuthMenu;
import mblog.core.persist.service.AuthMenuService;
import mblog.core.persist.service.ConfigService;
import mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 22:06.
 */
@Controller
@RequestMapping("/admin/config")
public class ConfigsController extends BaseController{

    @Autowired
    private ConfigService configService;

    @Autowired
    private AuthMenuService authMenuService;

    @RequestMapping("/")
    public String list(Model model){
        model.addAttribute("configs" , configService.findAll2Map());
        return "/admin/configs/main";
    }

    @ModelAttribute
    public void getAuthMenus(Model model){
        List<AuthMenu> authMenus = authMenuService.findByParentId(2);
        model.addAttribute("results" , authMenus);
    }
}

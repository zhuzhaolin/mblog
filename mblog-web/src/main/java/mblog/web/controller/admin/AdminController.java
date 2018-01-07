package mblog.web.controller.admin;

import mblog.core.data.AuthMenu;
import mblog.core.persist.service.AuthMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/3 22:20.
 */
@Controller
public class AdminController {

    @Autowired
    private AuthMenuService authMenuService;

    @RequestMapping("/admin")
    public String index(HttpServletRequest request , Model model){
        pushSystemStatus(request , model);
        return "admin/index";
    }

    private void pushSystemStatus(HttpServletRequest request, Model model) {
        float freeMemory = (float) Runtime.getRuntime().freeMemory();
        float totalMemory = (float) Runtime.getRuntime().totalMemory();
        float usedMemory = (totalMemory - freeMemory);
        float memPercent = Math.round(freeMemory / totalMemory * 100) ;
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");

        model.addAttribute("freeMemory", freeMemory);
        model.addAttribute("totalMemory", totalMemory / 1024 / 1024);
        model.addAttribute("usedMemory", usedMemory / 1024 / 1024);
        model.addAttribute("memPercent", memPercent);
        model.addAttribute("os", os);
        model.addAttribute("javaVersion", javaVersion);
    }

    @ModelAttribute
    public void getAuthMenus(Model model){
        List<AuthMenu> authMenus = authMenuService.findByParentId(2);
        model.addAttribute("results" , authMenus);
    }
}

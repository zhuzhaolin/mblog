package mblog.web.controller.admin;

import mblog.base.lang.Consts;
import mblog.core.data.AuthMenu;
import mblog.core.data.Post;
import mblog.core.persist.service.AuthMenuService;
import mblog.core.persist.service.PostService;
import mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/5 23:09.
 */
@Controller
@RequestMapping("/admin/posts")
public class PostsController extends BaseController{

    @Autowired
    private PostService postService;

    @Autowired
    AuthMenuService authMenuService;


    @GetMapping("/list")
    @ModelAttribute
    public String list(String title , Model model , HttpServletRequest request){
        long id = ServletRequestUtils.getLongParameter(request , "id" , Consts.ZERO);
        int group = ServletRequestUtils.getIntParameter(request , "group" , Consts.ZERO);

        Pageable pageable = wrapPageable();
        Page<Post> page = postService.paging4Admin(pageable , id , title , group);
        model.addAttribute("page" , page);
        model.addAttribute("title" , title);
        return "/admin/posts/list";
    }

    @ModelAttribute
    public void getAuthMenus(Model model){
        List<AuthMenu> authMenus = authMenuService.findByParentId(2);
        model.addAttribute("results" , authMenus);
    }
}

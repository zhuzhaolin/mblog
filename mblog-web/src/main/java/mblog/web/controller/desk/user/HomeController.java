package mblog.web.controller.desk.user;

import mblog.core.data.AccountProfile;
import mblog.core.data.Feeds;
import mblog.core.data.Post;
import mblog.core.data.User;
import mblog.core.persist.entity.FeedsPO;
import mblog.core.persist.entity.PostPO;
import mblog.core.persist.entity.UserPO;
import mblog.core.persist.service.FeedsService;
import mblog.core.persist.service.PostService;
import mblog.core.persist.service.UserService;
import mblog.shiro.authc.AccountSubject;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhuzhaolin on 2017/11/20.
 */
@Controller
public class HomeController extends BaseController {

    @Autowired
    private PostService postService;

    @Autowired
    private FeedsService feedsService;

    @Autowired
    private UserService userService;

    /**
     * 用户主页
     * @param model
     * @return
     */
    @RequestMapping(value = "/home")
    public String home( Model model) {
        Pageable pageable = wrapPageable();
        Page<Feeds> page = feedsService.findUserFeeds(pageable, getUserId());
        model.addAttribute("page", page);
        //暂时这样
        List<Post> posts = new ArrayList<>();
        page.getContent().forEach(feeds -> {
            posts.add(feeds.getPost());
        });
        model.addAttribute("posts" , posts);
        initUser(model);
        return getView(Views.HOME_FEEDS);
    }

    /**
     * 我发布的文章
     * @param pn
     * @param model
     * @return
     */
    @RequestMapping(value = "/home" , params = "method=posts")
    public String posts(Integer pn, Model model) {
        PageRequest pageRequest = wrapPage(pn);
        List<Post> posts = postService.pagingByAuthorId(pageRequest , getUserId());
        model.addAttribute("posts" , posts);
        initUser(model);
        return getView(Views.HOME_POSTS);
    }

    private void initUser(Model model) {
        User user = userService.get(getUserId());
        model.addAttribute("user", user);
    }

    public FeedsService getFeedsService() {
        return feedsService;
    }

    public void setFeedsService(FeedsService feedsService) {
        this.feedsService = feedsService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

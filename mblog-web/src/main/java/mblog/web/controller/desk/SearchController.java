package mblog.web.controller.desk;

import mblog.core.data.Post;
import mblog.core.persist.service.PostService;
import mblog.core.persist.service.TagService;
import mblog.web.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by zhuzhaolin .
 * Created in2017/12/30 10:12.
 */
@Controller
public class SearchController extends BaseController{

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @RequestMapping("/search")
    public String search(String q , Model model){
        Pageable pageable = wrapPageable();
        try {
            if (StringUtils.isNotEmpty(q)){
                Page<Post> page = postService.search(pageable , q);
                model.addAttribute("posts" , page.getContent());
            }
        }catch (Exception e){
          e.printStackTrace();
        }
        model.addAttribute("q" , q);
        return getView(Views.BROWSE_SEARCH);
    }
}

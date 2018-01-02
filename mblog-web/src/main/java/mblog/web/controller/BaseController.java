package mblog.web.controller;

import mblog.base.context.AppContext;
import mblog.base.upload.FileRepoFactory;
import mblog.core.data.AccountProfile;
import mblog.shiro.authc.AccountSubject;
import mtons.modules.pojos.Paging;
import mtons.modules.pojos.UserProfile;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by zhuzhaolin on 2017/11/20.
 */
public class BaseController {

    @Autowired
    protected AppContext appContext;

    @Autowired
    protected FileRepoFactory fileRepoFactory;


    /**
     * 获取登录信息
     */
    protected AccountSubject getSubject(){
        return (AccountSubject) SecurityUtils.getSubject();
    }

    protected String getView(String view){
        return "default" + view;
    }

    protected Pageable wrapPageable(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        int pageSize = ServletRequestUtils.getIntParameter(request , "pageSize" , 10);
        int pageNo = ServletRequestUtils.getIntParameter(request , "pn" , 1);
        return new PageRequest(pageNo - 1 , pageSize);
    }
    /**
     * 包装分页对象
     * @param pn 页码
     * @return
     */
    protected PageRequest wrapPage(Integer pn){
        if (pn == null || pn == 0){
            pn = 1;
        }
        return wrapPage(pn , 10);
    }

    /**
     * 包装分页对象
     */
    protected PageRequest wrapPage(Integer pn , Integer maxResults){
        if (pn == null || pn == 0){
            pn = 1;
        }
        if (maxResults == null || maxResults == 0){
            maxResults = 10;
        }
        return new PageRequest(0 , maxResults);
    }

    protected void putProfile(AccountProfile profile){
        SecurityUtils.getSubject().getSession(true).setAttribute("profile" , profile);
    }

    protected long getUserId(){
        return getSubject().getProfile().getId();
    }
}

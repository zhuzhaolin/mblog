package mblog.web.controller;

import mblog.base.data.DataExt;
import mblog.base.lang.Consts;
import mblog.core.data.AccountProfile;
import mblog.base.data.Data;
import mblog.core.persist.service.UserService;
import mblog.core.persist.service.VerifyService;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/17 22:46.
 */
@Controller
@RequestMapping("/email")
public class EmailController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerifyService verifyService;

    @RequestMapping(value = "/bind" , method = RequestMethod.GET)
    public String bind(String code , ModelMap model){
        DataExt data = null;
        UserProfile profile = getSubject().getProfile();
        try{
           verifyService.verify(profile.getId() , Consts.VERIFY_BIND , code);
            AccountProfile p = userService.updateActiveEmail(profile.getId() , Consts.ACTIVE_EMAIL);
            putProfile(p);

            data = DataExt.success("恭喜您!邮箱绑定成功." , Data.NOOP);
            data.addLink("index" , "返回首页");
        }catch (Exception e){
           data = DataExt.failure(e.getMessage());
        }
        model.put("type" , Consts.VERIFY_BIND);
      //  model.put("userId" , profile.getId());
        model.put("data" , data);
        return getView(Views.REG_RESULT);
    }
}

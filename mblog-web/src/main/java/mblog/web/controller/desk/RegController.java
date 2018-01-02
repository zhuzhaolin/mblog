package mblog.web.controller.desk;

import mblog.base.email.EmailSender;
import mblog.base.lang.Consts;
import mblog.core.data.AccountProfile;
import mblog.base.data.Data;
import mblog.core.data.User;
import mblog.core.persist.service.UserService;
import mblog.core.persist.service.VerifyService;
import mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.Context;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/1 23:09.
 */
@Controller
public class RegController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private VerifyService verifyService;

    @Autowired
    private EmailSender emailSender;

    @RequestMapping(value = "/reg" , method = RequestMethod.GET)
    public String view(){
        AccountProfile profile = getSubject().getProfile();
        if (profile != null){
            return "redirect:/home";
        }
        return getView(Views.REG);
    }

    @RequestMapping(value = "/reg" , method = RequestMethod.POST)
    public String reg(User post , Model model){
        Data data;
        String ret = getView(Views.REG);

        try{
            post.setAvatar(Consts.AVATAR);
            User user = userService.register(post);

            sendEmail(user);

            data = Data.success("恭喜您! 注册成功, 已经给您的邮箱发了验证码, 赶紧去完成邮箱绑定吧。", Data.NOOP);
            data.addLink("login", "先去登陆尝尝鲜");

            ret = getView(Views.REG_RESULT);
        }catch (Exception e){
          model.addAttribute("post" , post);
          data = Data.failure(e.getMessage());
        }
        model.addAttribute("data" , data);
        return ret;
    }

    private void sendEmail(User user){
        String code = verifyService.generateCode(user.getId() , Consts.VERIFY_BIND , user.getEmail());
        Context context = new Context();
        context.setVariable("code", code);
        emailSender.sendTemplate(user.getEmail(), "邮箱绑定验证", Consts.EMAIL_TEMPLATE_BIND, context);
    }
}

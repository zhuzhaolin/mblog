package mblog.web.controller.desk.account;


import mblog.base.email.EmailSender;
import mblog.base.lang.Consts;
import mblog.base.upload.ImageHandleUtils;
import mblog.base.utils.FilePathUtils;
import mblog.core.data.AccountProfile;
import mblog.base.data.Data;
import mblog.core.data.User;
import mblog.core.persist.service.UserService;
import mblog.core.persist.service.VerifyService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/5 22:22.
 */

@Controller
@RequestMapping("/account")
public class UserSettingController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    VerifyService verifyService;

/*    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender; */



    @Autowired
    private EmailSender emailSender;


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String view(Model model) {
        long userId = getUserId();
        User view = userService.get(userId);
        model.addAttribute("view", view);

        return getView(Views.ACCOUNT_PROFILE);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ResponseBody
    public Map baseSetting(User user, Model model) {
        Data data = null;
        Map<String, Data> datas = new HashMap<>();
        // UserProfile profile = getSubject().getProfile();

        try {
            long userId = getUserId();
            //   User user = new User();
            user.setId(userId);
            //  user.setName(name);
            //  user.setSignature(signature);
            putProfile(userService.update(user));

            User view = userService.get(userId);
            model.addAttribute("view", view);
            data = Data.success("操作成功", Data.NOOP);
        } catch (Exception e) {
            data = Data.failure(e.getMessage());
        }
        datas.put("message", data);
        return datas;
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Data> passwordSetting(String oldPassword, String password, Model model) {
        Data data = null;
        Map<String, Data> datas = new HashMap<>();
        try {
            UserProfile profile = getSubject().getProfile();
            userService.updatePassword(profile.getId(), oldPassword, password);

            data = Data.success("操作成功", Data.NOOP);
        } catch (Exception e) {
            data = Data.failure(e.getMessage());
        }
        datas.put("message", data);
        return datas;
    }

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    @ResponseBody
    public Data emailPost(String email, Model model) {
        Data data = null;
        UserProfile profile = getSubject().getProfile();

        try {
            Assert.notNull(email, "缺少必要的参数");

            //先执行修改，判断邮箱是否更改，或邮箱是否被人使用
            userService.updateEmail(profile.getId(), email);

            String code = verifyService.generateCode(profile.getId(), Consts.VERIFY_BIND, email);

            Context context = new Context();
            context.setVariable("code", code);
        /*    context.setVariable("userId", profile.getId());
            context.setVariable("code", code);
            context.setVariable("type", Consts.VERIFY_BIND);*/

            emailSender.sendTemplate(email, "邮箱绑定验证", Consts.EMAIL_TEMPLATE_BIND, context);

            data = Data.success("操作成功,已经发送验证邮件，请前往邮箱验证", Data.NOOP);
        } catch (Exception e) {
            data = Data.failure(e.getMessage());
        }


        return data;
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    @ResponseBody
    public Data post(MultipartFile file, Float x, Float y, Float width, Float height, ModelMap model) {
        Data data = null;
        AccountProfile profile = getSubject().getProfile();
        File temp = null;
        if (file.isEmpty()) {
            return Data.failure("请选择图片");
        }

        String root = fileRepoFactory.select().getRoot();
        temp = new File(root + File.separator + "store" + File.separator + file.getOriginalFilename());
        try {
            file.transferTo(temp);

            //对结果进行压缩
            ImageHandleUtils.scaleImage(temp.getAbsolutePath(), temp.getParent() + File.separator + "temp" + File.separator + file.getOriginalFilename(), 100);

            AccountProfile user = userService.updateAvatar(profile.getId(), File.separator + "store/temp" + File.separator + file.getOriginalFilename());
            putProfile(user);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               /* temp.delete();
                if (scale != null) {
                    scale.delete();
                }*/
            //下面这行代码放在这里是有问题的
            data = Data.success("操作成功", Data.NOOP);
        }


        return data;
    }

    private String getAvaPath(long uid, String extensionName, int size) {
        String base = FilePathUtils.getAvatar(uid);
        return String.format("/%s_%d" + extensionName, base, size);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public EmailSender getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public VerifyService getVerifyService() {
        return verifyService;
    }

    public void setVerifyService(VerifyService verifyService) {
        this.verifyService = verifyService;
    }
}

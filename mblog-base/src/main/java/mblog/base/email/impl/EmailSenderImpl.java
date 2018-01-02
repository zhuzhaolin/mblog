package mblog.base.email.impl;

import mblog.base.context.AppContext;
import mblog.base.email.EmailSender;
import mblog.base.exception.MtonsException;
import mblog.base.lang.SiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.util.StringUtils;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/16 10:58.
 */
@Service
@Lazy(false)
public class EmailSenderImpl implements EmailSender{

    private JavaMailSenderImpl sender;
    private String domain;
    private static boolean inited = false;


    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    private  AppContext appContext;


    @Override
    public void sendTemplate(String targetAddress, String subject, String template, Context data) {
       data.setVariable("domain" , getDomain());
       final String html = templateEngine.process(template , data);
       sendText(targetAddress , subject , html , true);
    }

    @Override
    public void sendText(String targetAddress, String subject, String content, boolean isHtml) {
        init();
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message , true , "UTF-8");
            messageHelper.setFrom(sender.getUsername());
            messageHelper.setSubject(subject);
            messageHelper.setTo(targetAddress);
            messageHelper.setText(content , isHtml);

            new Thread(()->{
                sender.send(message);
            }).start();
        }catch (Exception e){

        }
    }

    public void init() {
        // 如果加载完毕直接返回
        if (inited) {
            return;
        }

        String host = appContext.getConfig().get(SiteConfig.SITE_MAIL_HS);
        String username = appContext.getConfig().get(SiteConfig.SITE_MAIL_UN);
        String password = appContext.getConfig().get(SiteConfig.SITE_MAIL_PW);
        String port = appContext.getConfig().get(SiteConfig.SITE_MAIL_PORT);

        if (StringUtils.isEmpty(host) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new MtonsException(" 系统配置中的 mail.* 相关配置不完整, 不能正常使用邮件服务!");
        }

        sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(Integer.valueOf(port));
        sender.setUsername(username);
        sender.setPassword(password);

        Properties props = new Properties();
        props.setProperty("mail.smtp.auth" , "true");
        props.setProperty("mail.smtp.timeout" , "25000");
        props.setProperty("mail.smtp.socketFactory.port" , "587");
        props.setProperty("mail.smtp.socketFactory.fallback" , "false");
        sender.setJavaMailProperties(props);
    }

    private String getDomain(){
        if (domain == null){
            domain = appContext.getConfig().get(SiteConfig.SITE_DOMAIN);
            if (domain.endsWith("/")){
                domain = domain.substring(0 , domain.lastIndexOf("/"));
            }
        }
        return domain;
    }
}

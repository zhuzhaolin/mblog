package mblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/17 10:15.
 */
@Configuration
public class CommConfig {
   /* @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost("smtp.qq.com");
        mailSender.setPort(587);
        mailSender.setUsername("925930798@qq.com");
        mailSender.setPassword("uljnugizkaqzbejh");
        mailSender.setProtocol("smtp");
        Properties pro = new Properties();

        //登录SMTP服务器,需要获得授权，网易163邮箱新近注册的邮箱均不能授权。
        //测试 sohu 的邮箱可以获得授权
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.socketFactory.port", "587");
        pro.put("mail.smtp.socketFactory.fallback", "false");
        mailSender.setJavaMailProperties(pro);
        return mailSender;
    }*/


}

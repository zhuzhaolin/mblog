package mblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * Created by zhuzhaolin .
 * Created in2017/11/25 23:54.
 */
public class Test {


    public static void main(String[] args) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("你好");
        mailMessage.setText("这个是一个通过Spring框架来发送邮件的小程序");
        mailMessage.setTo("9197****1@qq.com");
        mailSender().send(mailMessage);


    }

    public static  MailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost("smtp.exmail.qq.com");
        mailSender.setUsername("zzl1993@hotmail.com");
        mailSender.setPassword("Zzl6128109");
        return mailSender;
    }
}

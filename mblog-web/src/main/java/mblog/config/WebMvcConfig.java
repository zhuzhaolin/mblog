package mblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

/**
 * Created by zhuzhaolin on 2017/10/5.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
       // registry.addResourceHandler("/templates*//**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/store/**").addResourceLocations("file:/F:/picture/store/");
        registry.addResourceHandler("/store/ava/**").addResourceLocations("file:/F:/picture/store/ava/");
    }





}

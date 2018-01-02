package mblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"mblog.config" , "mblog.shiro" , "mblog.web" , "mblog.base" , "mblog.core"})
public class MblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MblogApplication.class, args);
	}
}

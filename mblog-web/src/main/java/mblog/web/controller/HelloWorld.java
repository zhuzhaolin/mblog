package mblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhuzhaolin on 2017/11/18.
 */
@Controller
public class HelloWorld {
    @RequestMapping(value="/hello" , method = RequestMethod.GET)
    public String home(){
        System.out.println("?????");
        return "helloWorld";
    }
}

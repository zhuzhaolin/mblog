package mblog.web.listener;

import mblog.base.context.AppContext;
import mblog.base.lang.Consts;
import mblog.core.data.Config;
import mblog.core.persist.service.ConfigService;
import mblog.core.persist.service.GroupService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/17 19:36.
 */
@Component
public class StartupListener implements InitializingBean , ServletContextAware{

    @Autowired
    private ConfigService configService;

    @Autowired
    private AppContext appContext;

    @Autowired
    GroupService groupService;

    private ServletContext servletContext;

    private int period = 0;



    /**
     *加载配置到信息系统
     */
    private void loadConfig(){

        Timer timer = new Timer("loadConfig" , true);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Config> configs = configService.findAll();
                Map<String , String> configMap = new HashMap<>();
                if (configs.isEmpty()){
                    System.exit(1);
                }else {
                    configs.forEach(config -> {
                        configMap.put(config.getKey() , config.getValue());
                    });
                }
                appContext.setConfig(configMap);

                servletContext.setAttribute("groups" , groupService.findAll(Consts.STATUS_NORMAL));
            }
        } , period * Consts.TIME_MIN);
        period = 3;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
       loadConfig();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

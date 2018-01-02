package mblog.base.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/7 22:38.
 */

@Component
public class AppContext implements ServletContextAware {

    /**
     *
     */
    @Value("${store.root}")
    private String root = "/data/mblog";

    /*
	 * 文件存储-头像目录
	 */
    String avaDir = "/store/ava";

    /*
    * 文件存储-临时文件目录
   */
    String tempDir = "/store/temp";

    /**
     * 系统配置信息
     * 在 StartupListener 类中加载
     */
    public Map<String , String> config;


    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }


    public String getAvaDir() {
        return avaDir;
    }

    public void setAvaDir(String avaDir) {
        this.avaDir = avaDir;
    }

    public String getTempDir() {
        return tempDir;
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {

    }
}

package mblog.base.email;

import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/6 22:06.
 */
public interface EmailSender {
    void sendTemplate(String targetAddress, String subject, String template, Context data);

    void sendText(String targetAddress, String subject, String content, boolean isHtml);
}

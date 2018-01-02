package mblog.core.data;

import java.io.Serializable;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
public class BadgesCount implements Serializable {

    /**
     * 通知数量
     */
    private int notifies;

    /**
     * 私信数量
     */
    private int messages;

    public int getNotifies() {
        return notifies;
    }

    public void setNotifies(int notifies) {
        this.notifies = notifies;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }
}

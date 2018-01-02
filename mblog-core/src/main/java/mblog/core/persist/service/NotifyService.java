package mblog.core.persist.service;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
public interface NotifyService {

    /**
     * 未读消息数量
     */
    public int unread4Me(long ownId);
}

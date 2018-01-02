package mblog.core.persist.dao;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
public interface NotifyDao {

    /**
     * 查询我的未读消息
     */
    public int unread4Me(long ownId);
}

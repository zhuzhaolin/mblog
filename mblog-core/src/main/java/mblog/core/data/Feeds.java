package mblog.core.data;

import mblog.core.persist.entity.FeedsPO;

import java.util.Date;

/**
 * 订阅
 * Created by zhuzhaolin on 2017/11/24.
 */
public class Feeds extends FeedsPO {
  private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

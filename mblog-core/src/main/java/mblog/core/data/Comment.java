package mblog.core.data;

import mblog.core.persist.entity.CommentPO;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
public class Comment extends CommentPO implements Serializable {

    private static final long serialVersionUID = -6281702977461252164L;

    private User author;
    private Comment parent;
    private Post post;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

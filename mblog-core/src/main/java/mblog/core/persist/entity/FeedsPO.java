package mblog.core.persist.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhuzhaolin on 2017/11/21.
 */
@Entity
@Table(name = "mto_feeds")
public class FeedsPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 所属用户Id
     */
    @Column(name = "own_id")
    private long ownId;

    private int type;

    /**
     * 目标Id
     */
    @Column(name = "post_id")
    private long postId;

    /**
     * 作者
     */
    @Column(name = "author_id")
    private long authorId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnId() {
        return ownId;
    }

    public void setOwnId(long ownId) {
        this.ownId = ownId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

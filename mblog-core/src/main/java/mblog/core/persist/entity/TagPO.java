package mblog.core.persist.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessorOrder;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/30 10:18.
 */
@Entity
@Table(name = "mto_tags")
public class TagPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name" , unique = true , length = 32)
    private String name;

    @Column(name = "last_post_id")
    private long lastPostId;

    /**
     * 是否推荐
     */
    private int featured;

    /**
     * 文章数
     */
    private int posts;

    /**
     * 热度
     */
    private int hots;

    /**
     * 是否锁定
     */
    private int locked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLastPostId() {
        return lastPostId;
    }

    public void setLastPostId(long lastPostId) {
        this.lastPostId = lastPostId;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public int getHots() {
        return hots;
    }

    public void setHots(int hots) {
        this.hots = hots;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }
}

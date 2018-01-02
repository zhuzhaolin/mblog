package mblog.core.persist.entity;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhuzhaolin on 2017/11/24.
 */
@Entity
@Table(name = "mto_posts")
@Indexed
@Analyzer(impl = SmartChineseAnalyzer.class)
public class PostPO implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    private long id;

    /**
     * 分组/模块ID
     */
    @Field
    @NumericField
    @Column(name = "group_" , length = 5)
    private int group;

    /**
     * 标题
     */
    @Field
    @Column(name = "title" , length = 64)
    private String title;

    /**
     * 摘要
     */
    @Field
    private String summary;

    /**
     * 标签，多个逗号隔开
     */
    @Field
    private String tags;

    /**
     * 作者
     */
    @Field
    @NumericField
    @Column(name = "author_id")
    private long authorId;

    /**
     * 编辑器（ueditor/markdown)
     */
    private String editor;


    /**
     * 文章最后AttachId
     * - 冗余字段
     */
    @Column(name = "last_image_id")
    private long lastImageId;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    /**
     * 图片统计
     */
    private int images;

    /**
     * 推荐状态
     */
    private int featured;

    /**
     * 喜欢数
     */
    private int favors;

    /**
     * 评论数
     */
    private int comments;

    /**
     * 阅读数
     */
    private int views;

    /**
     * 文章状态
     */
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public long getLastImageId() {
        return lastImageId;
    }

    public void setLastImageId(long lastImageId) {
        this.lastImageId = lastImageId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public int getFavors() {
        return favors;
    }

    public void setFavors(int favors) {
        this.favors = favors;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
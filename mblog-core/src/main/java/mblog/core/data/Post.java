package mblog.core.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mblog.base.lang.Consts;
import mblog.core.persist.entity.PostAttribute;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
public class Post implements Serializable {

    private static final long serialVersionUID = -8370322033004319452L;
    private long id;
    private int group;
    private String title;
    private String summary;

    @JsonIgnore
    private String content;

    /**
     * markdown内容
     */
    @JsonIgnore
    private String markdown;

    /**
     * 编辑器
     */
    private String editor;

    /**
     * 标签字符串
     */
    private String tags;

    /**
     * 创建时间
     */
    private Date created;

    private long authorId;

    private long lastImageId;

    /**
     * 图片统计
     */
    private int images;

    /**
     * 推荐状态
     */
    private int featured;

    /**
     * 喜欢
     */
    private int favors;

    private int comments;

    /**
     * 阅读
     */
    private int views;

    private int status;

    private List<Attach> albums;

    private Attach album;

    private User author;

    @JsonIgnore
    private PostAttribute attribute;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getTags() {
        return tags;
    }

    public String[] getTagsArray() {
        if (StringUtils.isNotBlank(tags)) {
            return tags.split(Consts.SEPARATOR);
        }
        return null;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getLastImageId() {
        return lastImageId;
    }

    public void setLastImageId(long lastImageId) {
        this.lastImageId = lastImageId;
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

    public List<Attach> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Attach> albums) {
        this.albums = albums;
    }

    public Attach getAlbum() {
        return album;
    }

    public void setAlbum(Attach album) {
        this.album = album;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public PostAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(PostAttribute attribute) {
        this.attribute = attribute;
    }
}

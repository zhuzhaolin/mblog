package mblog.core.persist.entity;

import javax.persistence.*;

/**
 * Created by zhuzhaolin on 2017/11/25.
 */
@Entity
@Table(name = "mto_attachs")
public class AttachPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 存储方式:本地/网络
     * 0:local , 1:network
     */
    private int store;

    /**
     * 所属内容Id
     */
    @Column(name = "to_id")
    private long toId;

    /**
     * 原图地址
     */
    private String original;

    /**
     * 等比预览图
     */
    private String preview;

    /**
     * 快照 225 x 140
     */
    private String screenshot;

    private int width;

    private int height;

    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

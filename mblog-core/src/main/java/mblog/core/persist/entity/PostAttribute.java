package mblog.core.persist.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
@Entity
@Table(name = "mto_posts_attribute")
public class PostAttribute implements Serializable{

    private static final long serialVersionUID = -2561061687589000057L;
    @Id
    private long id;

    /**
     * 内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

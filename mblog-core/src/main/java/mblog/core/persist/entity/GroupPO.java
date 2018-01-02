package mblog.core.persist.entity;

import javax.persistence.*;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/1 11:59.
 */
@Entity
@Table(name = "mto_group")
public class GroupPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    /**
     * 唯一关键字
     */
    @Column(name = "key_" , unique = true)
    private String key;

    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

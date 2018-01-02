package mblog.core.persist.entity;

import org.springframework.stereotype.Service;

import javax.persistence.*;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/17 19:40.
 */
@Entity
@Table(name = "mto_config")
public class ConfigPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int type;

    @Column(name = "key_" , unique = true)
    private String key;

    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

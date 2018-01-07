package mblog.core.persist.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhaolin on 2017/10/28.
 */
@Entity
@Table(name = "mto_role")
public class RolePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
    @JoinTable(name = "mto_role_menu", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
    @Fetch(FetchMode.SUBSELECT)
    private List<AuthMenuPO> authMenus = new ArrayList<>();

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

    public List<AuthMenuPO> getAuthMenus() {
        return authMenus;
    }

    public void setAuthMenus(List<AuthMenuPO> authMenus) {
        this.authMenus = authMenus;
    }
}

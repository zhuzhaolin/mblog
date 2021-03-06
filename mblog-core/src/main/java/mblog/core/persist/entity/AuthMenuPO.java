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
@Table(name = "mto_auth_menu")
public class AuthMenuPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String url;

    private int sort;

    private String permission;

    @JoinColumn(name = "parent_id")
    private long parentId;

    @Column(name = "parent_ids")
    private String parentIds;

    private String icon;

    @ManyToMany(mappedBy = "authMenus" , fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<RolePO> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mto_role_menu" , joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
    private List<AuthMenuPO> children = new ArrayList<>();

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<RolePO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolePO> roles) {
        this.roles = roles;
    }

    public List<AuthMenuPO> getChildren() {
        return children;
    }

    public void setChildren(List<AuthMenuPO> children) {
        this.children = children;
    }
}

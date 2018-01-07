package mblog.core.data;

import java.io.Serializable;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/7 0:05.
 */
public class FriendLink implements Serializable {

    private long id;
    private String siteName;
    private String url;
    private String remark;
    private String logn;
    private String sort;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogn() {
        return logn;
    }

    public void setLogn(String logn) {
        this.logn = logn;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}

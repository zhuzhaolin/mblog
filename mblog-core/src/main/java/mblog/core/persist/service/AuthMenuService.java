package mblog.core.persist.service;

import mblog.core.data.AuthMenu;

import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/4 21:55.
 */
public interface AuthMenuService {

    List<AuthMenu> findByParentId(long parentId);

    List<AuthMenu> listAll();
}

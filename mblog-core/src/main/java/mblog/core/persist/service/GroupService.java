package mblog.core.persist.service;

import mblog.core.data.Group;

import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/1 11:54.
 */
public interface GroupService {

    List<Group> findAll(int status);
}

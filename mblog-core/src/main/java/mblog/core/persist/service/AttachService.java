package mblog.core.persist.service;

import mblog.core.data.Attach;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhuzhaolin on 2017/11/25.
 */
public interface AttachService {

    /**
     * 批量查询附件
     *
     * @param toIds 目录对象Id列表
     * @return Map<toId, List<Attach>>
     */
    Map<Long , List<Attach>> findByTarget(Set<Long> toIds);

}

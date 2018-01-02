package mblog.core.persist.service;

import mblog.core.data.Post;
import mblog.core.persist.entity.PostPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhuzhaolin on 2017/11/24.
 */
public interface PostService {

    /**
     * 根据关键字搜索
     * @param pageable
     * @param q
     * @return
     * @throws Exception
     */
    Page<Post> search(Pageable pageable , String q) throws Exception;

    /**
     * 根据Ids查询 - 多图
     * @param ids
     * @return <id, 文章对象>
     */
    Map<Long , Post> findMultimeMapByIds(Set<Long> ids);

   List<Post> pagingByAuthorId(PageRequest pageRequest , long userId);
}

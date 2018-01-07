package mblog.core.persist.service;

import mblog.core.data.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 18:15.
 */
public interface CommentService {
    Page<Comment> paging4Admin(Pageable pageable);
}

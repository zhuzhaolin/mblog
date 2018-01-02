package mblog.core.persist.dao.custom;

import mblog.core.data.Post;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/1 16:52.
 */
public interface PostDaoCustom {
    Page<Post> search(Pageable pageable , String q) throws Exception;
}

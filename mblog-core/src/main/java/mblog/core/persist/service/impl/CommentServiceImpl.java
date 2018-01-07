package mblog.core.persist.service.impl;

import mblog.core.data.Comment;
import mblog.core.data.User;
import mblog.core.persist.dao.CommentDao;
import mblog.core.persist.entity.CommentPO;
import mblog.core.persist.service.CommentService;
import mblog.core.persist.service.UserService;
import mblog.core.persist.utils.BeanMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/6 18:18.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

   @Autowired
    private UserService userService;
    @Override
    @Transactional(readOnly = true)
    public Page<Comment> paging4Admin(Pageable pageable) {
        Page<CommentPO> page = commentDao.findAll(pageable);
        List<Comment> rets = new ArrayList<>();

        HashSet<Long> uids = new HashSet<>();

        page.getContent().forEach(po ->{
            uids.add(po.getAuthorId());
            rets.add(BeanMapUtils.copy(po));
        });
        buildUsers(rets , uids);
        return new PageImpl<Comment>(rets , pageable , page.getTotalElements());
    }

   private void buildUsers(Collection<Comment> posts, Set<Long> uids) {
        Map<Long, User> userMap = userService.findMapByIds(uids);

        posts.forEach(p -> p.setAuthor(userMap.get(p.getAuthorId())));
    }
}

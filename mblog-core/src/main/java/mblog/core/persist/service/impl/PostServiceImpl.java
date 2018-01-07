package mblog.core.persist.service.impl;

import mblog.base.lang.Consts;
import mblog.core.data.Attach;
import mblog.core.data.Post;
import mblog.core.data.User;
import mblog.core.persist.dao.PostDao;
import mblog.core.persist.entity.PostPO;
import mblog.core.persist.service.AttachService;
import mblog.core.persist.service.PostService;
import mblog.core.persist.service.UserService;
import mblog.core.persist.utils.BeanMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by zhuzhaolin on 2017/11/24.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Autowired
    AttachService attachService;

    @Autowired
    UserService userService;


    @Override
    public Page<Post> paging4Admin(Pageable pageable, long id, String title, int group) {
        Page<PostPO> page = postDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("featured")),
                    criteriaBuilder.desc(root.get("created")));
            Predicate predicate = criteriaBuilder.conjunction();
            if (group > Consts.ZERO) {
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("group").as(Integer.class), group));
            }

            if (StringUtils.isEmpty(title)) {
                predicate.getExpressions().add(
                        criteriaBuilder.like(root.get("title").as(String.class), "%" + title + "%"));
            }

            if (id > Consts.ZERO){
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("id").as(Integer.class) , id));
            }
            return predicate;
        }, pageable);

        return new PageImpl(toPosts(page.getContent() , false) , pageable , page.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> search(Pageable pageable, String q) throws Exception {
        Page<Post> page = postDao.search(pageable, q);

        HashSet<Long> ids = new HashSet<>();
        HashSet<Long> uids = new HashSet<>();

        for (Post po : page.getContent()) {
            ids.add(po.getId());
            uids.add(po.getAuthorId());
        }
        //加载相册
        buildAttachs(page.getContent(), ids);

        //加载用户信息
        buildUser(page.getContent(), uids);
        return page;
    }

    @Override
    public Map<Long, Post> findMultimeMapByIds(Set<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }

        List<PostPO> list = postDao.findAll(new Specification<PostPO>() {
            @Override
            public Predicate toPredicate(Root<PostPO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // criteriaQuery.where(criteriaBuilder.in(root.in(ids)));
                // return criteriaBuilder.in(root.in(ids));
                Path<Long> id = root.get("id");
                criteriaQuery.where(id.in(ids));
                return null;
            }
        });

        /*List<PostPO> list = postDao.findById(ids);*/

        Map<Long, Post> rets = new HashMap<>();
        HashSet<Long> uids = new HashSet<>();

        list.forEach(postPO -> {
            rets.put(postPO.getId(), BeanMapUtils.copy(postPO, 0));
            uids.add(postPO.getAuthorId());
        });

        //加载相册
        buildAttachs(rets.values(), ids);

        //加载用户信息
        buildUser(rets.values(), uids);

        return rets;
    }

    @Override
    public List<Post> pagingByAuthorId(PageRequest pageRequest, long userId) {
        List<PostPO> postPOS = postDao.findAll(new Specification<PostPO>() {
            @Override
            public Predicate toPredicate(Root<PostPO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Integer> authorId = root.get("authorId");
                Path<Date> created = root.get("created");
                query.orderBy(cb.desc(created));
                query.where(cb.equal(authorId, userId));
                return null;
            }
        }, pageRequest).getContent();

        return toPosts(postPOS, true);
    }


    private List<Post> toPosts(List<PostPO> posts, boolean whetherHasAlbums) {
        List<Post> rets = new ArrayList<>();

        HashSet<Long> pids = new HashSet<>();
        HashSet<Long> uids = new HashSet<>();

        posts.forEach(postPO -> {
            pids.add(postPO.getId());
            uids.add(postPO.getAuthorId());

            rets.add(BeanMapUtils.copy(postPO, 0));
        });
        //加载用户信息
        buildUser(rets, uids);

        //判断是否加载相册
        if (whetherHasAlbums) {
            buildAttachs(rets, pids);
        }

        return rets;
    }

    private void buildAttachs(Collection<Post> posts, Set<Long> postIds) {
        Map<Long, List<Attach>> attMap = attachService.findByTarget(postIds);
        posts.forEach(post -> {
            post.setAlbums(attMap.get(post.getId()));
        });
    }

    private void buildUser(Collection<Post> posts, Set<Long> uids) {
        Map<Long, User> userMap = userService.findMapByIds(uids);
        posts.forEach(post -> post.setAuthor(userMap.get(post.getAuthorId())));
    }
}

package mblog.core.persist.service.impl;

import groovy.util.IFileNameFinder;
import mblog.core.data.Attach;
import mblog.core.persist.dao.AttachDao;
import mblog.core.persist.entity.AttachPO;
import mblog.core.persist.service.AttachService;
import mblog.core.persist.utils.BeanMapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created by zhuzhaolin on 2017/11/25.
 */
@Service
public class AttachServiceImpl implements AttachService{

    @Autowired
    AttachDao attachDao;

    @Override
    public Map<Long, List<Attach>> findByTarget(Set<Long> toIds) {

        if (ObjectUtils.isEmpty(toIds)){
            return Collections.emptyMap();
        }
        List<AttachPO> list = attachDao.findAll(new Specification<AttachPO>() {
            @Override
            public Predicate toPredicate(Root<AttachPO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path<Long> id = root.get("id");
                criteriaQuery.where(id.in(toIds));
                return null;
            }
        });

        Map<Long , List<Attach>> ret = new HashMap<>();

        list.forEach(attachPO -> {
            Attach a = BeanMapUtils.copy(attachPO);

            List<Attach> ats = ret.get(a.getToId());

            if (ats == null){
                ats = new ArrayList<>();
                ret.put(a.getToId() , ats);
            }
            ats.add(a);
        });

        return ret;
    }
}

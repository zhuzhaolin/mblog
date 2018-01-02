package mblog.core.persist.dao.impl;

import mblog.core.data.Post;
import mblog.core.persist.dao.custom.PostDaoCustom;
import mblog.core.persist.entity.PostPO;
import mblog.core.persist.utils.BeanMapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhaolin .
 * Created in2018/1/1 16:54.
 */
public class PostDaoImpl implements PostDaoCustom{

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Post> search(Pageable pageable, String q) throws Exception {
        FullTextEntityManager fullTextSession = Search.getFullTextEntityManager(entityManager);

        SearchFactory sf = fullTextSession.getSearchFactory();
        QueryBuilder qb = sf.buildQueryBuilder().forEntity(PostPO.class).get();

        Query luceneQuery  = qb.keyword().onFields("title","summary","tags")
                .matching(q).createQuery();

        FullTextQuery query = fullTextSession.createFullTextQuery(luceneQuery);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span style='color:red;'>", "</span>");
        QueryScorer queryScorer = new QueryScorer(luceneQuery);
        Highlighter highlighter = new Highlighter(formatter, queryScorer);

        List<PostPO> list = query.getResultList();
        List<Post> rets = new ArrayList<>(list.size());

        for (PostPO po : list) {
            Post m = BeanMapUtils.copy(po, 0);

            // 处理高亮
            String title = highlighter.getBestFragment(standardAnalyzer, "title", m.getTitle());
            String summary = highlighter.getBestFragment(standardAnalyzer, "summary", m.getSummary());

            if (StringUtils.isNotEmpty(title)) {
                m.setTitle(title);
            }
            if (StringUtils.isNotEmpty(summary)) {
                m.setSummary(summary);
            }
            rets.add(m);
        }
        return new PageImpl<>(rets, pageable, query.getResultSize());
    }
}

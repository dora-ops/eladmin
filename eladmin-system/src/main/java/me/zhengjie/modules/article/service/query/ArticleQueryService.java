package me.zhengjie.modules.article.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.modules.article.service.dto.ArticleDTO;
import me.zhengjie.modules.article.repository.ArticleRepository;
import me.zhengjie.modules.article.service.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "article")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ArticleQueryService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ArticleDTO article, Pageable pageable){
        Page<Article> page = articleRepository.findAll(new Spec(article),pageable);
        return PageUtil.toPage(page.map(articleMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ArticleDTO article){
        return articleMapper.toDto(articleRepository.findAll(new Spec(article)));
    }

    class Spec implements Specification<Article> {

        private ArticleDTO article;

        public Spec(ArticleDTO article){
            this.article = article;
        }

        @Override
        public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(article.getTitle())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("title").as(String.class),"%"+article.getTitle()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
package me.zhengjie.modules.pubContent.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.pubContent.domain.PubContent;
import me.zhengjie.modules.pubContent.service.dto.PubContentDTO;
import me.zhengjie.modules.pubContent.repository.PubContentRepository;
import me.zhengjie.modules.pubContent.service.mapper.PubContentMapper;
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
@CacheConfig(cacheNames = "pubContent")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PubContentQueryService {

    @Autowired
    private PubContentRepository pubContentRepository;

    @Autowired
    private PubContentMapper pubContentMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PubContentDTO pubContent, Pageable pageable){
        Page<PubContent> page = pubContentRepository.findAll(new Spec(pubContent),pageable);
        return PageUtil.toPage(page.map(pubContentMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PubContentDTO pubContent){
        return pubContentMapper.toDto(pubContentRepository.findAll(new Spec(pubContent)));
    }

    class Spec implements Specification<PubContent> {

        private PubContentDTO pubContent;

        public Spec(PubContentDTO pubContent){
            this.pubContent = pubContent;
        }

        @Override
        public Predicate toPredicate(Root<PubContent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(pubContent.getTitle())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("title").as(String.class),"%"+pubContent.getTitle()+"%"));
            }
            if(!ObjectUtils.isEmpty(pubContent.getCusId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("cus_id").as(String.class),pubContent.getCusId()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
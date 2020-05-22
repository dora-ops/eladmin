package me.zhengjie.modules.suggest.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.suggest.domain.Suggest;
import me.zhengjie.modules.suggest.service.dto.SuggestDTO;
import me.zhengjie.modules.suggest.repository.SuggestRepository;
import me.zhengjie.modules.suggest.service.mapper.SuggestMapper;
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
@CacheConfig(cacheNames = "suggest")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SuggestQueryService {

    @Autowired
    private SuggestRepository suggestRepository;

    @Autowired
    private SuggestMapper suggestMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SuggestDTO suggest, Pageable pageable){
        Page<Suggest> page = suggestRepository.findAll(new Spec(suggest),pageable);
        return PageUtil.toPage(page.map(suggestMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SuggestDTO suggest){
        return suggestMapper.toDto(suggestRepository.findAll(new Spec(suggest)));
    }

    class Spec implements Specification<Suggest> {

        private SuggestDTO suggest;

        public Spec(SuggestDTO suggest){
            this.suggest = suggest;
        }

        @Override
        public Predicate toPredicate(Root<Suggest> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(suggest.getId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("id").as(Long.class),suggest.getId()));
            }
            if(!ObjectUtils.isEmpty(suggest.getText())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("text").as(String.class),"%"+suggest.getText()+"%"));
            }
            if(!ObjectUtils.isEmpty(suggest.getStuid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("stuid").as(String.class),suggest.getStuid()));
            }
            if(!ObjectUtils.isEmpty(suggest.getStuname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("stuname").as(String.class),"%"+suggest.getStuname()+"%"));
            }
            if(!ObjectUtils.isEmpty(suggest.getTeaid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("teaid").as(Long.class),suggest.getTeaid()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
package me.zhengjie.modules.spider.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.spider.domain.Spider;
import me.zhengjie.modules.spider.service.dto.SpiderDTO;
import me.zhengjie.modules.spider.repository.SpiderRepository;
import me.zhengjie.modules.spider.service.mapper.SpiderMapper;
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
@CacheConfig(cacheNames = "spider")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SpiderQueryService {

    @Autowired
    private SpiderRepository spiderRepository;

    @Autowired
    private SpiderMapper spiderMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SpiderDTO spider, Pageable pageable){
        Page<Spider> page = spiderRepository.findAll(new Spec(spider),pageable);
        return PageUtil.toPage(page.map(spiderMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SpiderDTO spider){
        return spiderMapper.toDto(spiderRepository.findAll(new Spec(spider)));
    }

    class Spec implements Specification<Spider> {

        private SpiderDTO spider;

        public Spec(SpiderDTO spider){
            this.spider = spider;
        }

        @Override
        public Predicate toPredicate(Root<Spider> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(spider.getCode())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("code").as(String.class),spider.getCode()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
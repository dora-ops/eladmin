package me.zhengjie.modules.express.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.express.domain.Express;
import me.zhengjie.modules.express.service.dto.ExpressDTO;
import me.zhengjie.modules.express.repository.ExpressRepository;
import me.zhengjie.modules.express.service.mapper.ExpressMapper;
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
@CacheConfig(cacheNames = "express")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ExpressQueryService {

    @Autowired
    private ExpressRepository expressRepository;

    @Autowired
    private ExpressMapper expressMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExpressDTO express, Pageable pageable){
        Page<Express> page = expressRepository.findAll(new Spec(express),pageable);
        return PageUtil.toPage(page.map(expressMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExpressDTO express){
        return expressMapper.toDto(expressRepository.findAll(new Spec(express)));
    }

    class Spec implements Specification<Express> {

        private ExpressDTO express;

        public Spec(ExpressDTO express){
            this.express = express;
        }

        @Override
        public Predicate toPredicate(Root<Express> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(express.getTitle())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("title").as(String.class),"%"+express.getTitle()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
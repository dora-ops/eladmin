package me.zhengjie.modules.spider_info.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import me.zhengjie.modules.spider_info.service.dto.SpiderInfoDTO;
import me.zhengjie.modules.spider_info.repository.SpiderInfoRepository;
import me.zhengjie.modules.spider_info.service.mapper.SpiderInfoMapper;
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
@CacheConfig(cacheNames = "spiderInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SpiderInfoQueryService {

    @Autowired
    private SpiderInfoRepository spiderInfoRepository;

    @Autowired
    private SpiderInfoMapper spiderInfoMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SpiderInfoDTO spiderInfo, Pageable pageable){
        Page<SpiderInfo> page = spiderInfoRepository.findAll(new Spec(spiderInfo),pageable);
        return PageUtil.toPage(page.map(spiderInfoMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SpiderInfoDTO spiderInfo){
        return spiderInfoMapper.toDto(spiderInfoRepository.findAll(new Spec(spiderInfo)));
    }

    class Spec implements Specification<SpiderInfo> {

        private SpiderInfoDTO spiderInfo;

        public Spec(SpiderInfoDTO spiderInfo){
            this.spiderInfo = spiderInfo;
        }

        @Override
        public Predicate toPredicate(Root<SpiderInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(spiderInfo.getDomain())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("domain").as(String.class),"%"+spiderInfo.getDomain()+"%"));
            }
            if(!ObjectUtils.isEmpty(spiderInfo.getSiteName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("site_name").as(String.class),"%"+spiderInfo.getSiteName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
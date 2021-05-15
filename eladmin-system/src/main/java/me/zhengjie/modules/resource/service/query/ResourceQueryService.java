package me.zhengjie.modules.resource.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.resource.domain.Resource;
import me.zhengjie.modules.resource.service.dto.ResourceDTO;
import me.zhengjie.modules.resource.repository.ResourceRepository;
import me.zhengjie.modules.resource.service.mapper.ResourceMapper;
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
@CacheConfig(cacheNames = "resource")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ResourceQueryService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ResourceDTO resource, Pageable pageable){
        Page<Resource> page = resourceRepository.findAll(new Spec(resource),pageable);
        return PageUtil.toPage(page.map(resourceMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ResourceDTO resource){
        return resourceMapper.toDto(resourceRepository.findAll(new Spec(resource)));
    }

    class Spec implements Specification<Resource> {

        private ResourceDTO resource;

        public Spec(ResourceDTO resource){
            this.resource = resource;
        }

        @Override
        public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(resource.getFilename())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("filename").as(String.class),"%"+resource.getFilename()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
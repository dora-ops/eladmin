package me.zhengjie.modules.tea_resource.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.tea_resource.domain.TeaResource;
import me.zhengjie.modules.tea_resource.service.dto.TeaResourceDTO;
import me.zhengjie.modules.tea_resource.repository.TeaResourceRepository;
import me.zhengjie.modules.tea_resource.service.mapper.TeaResourceMapper;
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
@CacheConfig(cacheNames = "teaResource")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TeaResourceQueryService {

    @Autowired
    private TeaResourceRepository teaResourceRepository;

    @Autowired
    private TeaResourceMapper teaResourceMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TeaResourceDTO teaResource, Pageable pageable){
        Page<TeaResource> page = teaResourceRepository.findAll(new Spec(teaResource),pageable);
        return PageUtil.toPage(page.map(teaResourceMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TeaResourceDTO teaResource){
        return teaResourceMapper.toDto(teaResourceRepository.findAll(new Spec(teaResource)));
    }

    class Spec implements Specification<TeaResource> {

        private TeaResourceDTO teaResource;

        public Spec(TeaResourceDTO teaResource){
            this.teaResource = teaResource;
        }

        @Override
        public Predicate toPredicate(Root<TeaResource> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(teaResource.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+teaResource.getName()+"%"));
            }
            if(!ObjectUtils.isEmpty(teaResource.getType())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("type").as(Integer.class),teaResource.getType()));
            }
            if(!ObjectUtils.isEmpty(teaResource.getFileType())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("fileType").as(String.class),teaResource.getFileType()));
            }
            if(!ObjectUtils.isEmpty(teaResource.getCreateId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("createId").as(String.class),teaResource.getCreateId()));
            }
            if(!ObjectUtils.isEmpty(teaResource.getCreateName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("createName").as(String.class),"%"+teaResource.getCreateName()+"%"));
            }
            if(!ObjectUtils.isEmpty(teaResource.getClaId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("claId").as(Integer.class),teaResource.getClaId()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
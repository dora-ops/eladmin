package me.zhengjie.modules.handleInfo.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.handleInfo.domain.HandleInfo;
import me.zhengjie.modules.handleInfo.service.dto.HandleInfoDTO;
import me.zhengjie.modules.handleInfo.repository.HandleInfoRepository;
import me.zhengjie.modules.handleInfo.service.mapper.HandleInfoMapper;
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
@CacheConfig(cacheNames = "handleInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class HandleInfoQueryService {

    @Autowired
    private HandleInfoRepository handleInfoRepository;

    @Autowired
    private HandleInfoMapper handleInfoMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(HandleInfoDTO handleInfo, Pageable pageable){
        Page<HandleInfo> page = handleInfoRepository.findAll(new Spec(handleInfo),pageable);
        return PageUtil.toPage(page.map(handleInfoMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(HandleInfoDTO handleInfo){
        return handleInfoMapper.toDto(handleInfoRepository.findAll(new Spec(handleInfo)));
    }

    class Spec implements Specification<HandleInfo> {

        private HandleInfoDTO handleInfo;

        public Spec(HandleInfoDTO handleInfo){
            this.handleInfo = handleInfo;
        }

        @Override
        public Predicate toPredicate(Root<HandleInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(handleInfo.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+handleInfo.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
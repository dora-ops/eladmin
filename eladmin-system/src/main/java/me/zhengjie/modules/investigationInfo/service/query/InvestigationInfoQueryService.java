package me.zhengjie.modules.investigationInfo.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.investigationInfo.domain.InvestigationInfo;
import me.zhengjie.modules.investigationInfo.service.dto.InvestigationInfoDTO;
import me.zhengjie.modules.investigationInfo.repository.InvestigationInfoRepository;
import me.zhengjie.modules.investigationInfo.service.mapper.InvestigationInfoMapper;
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
@CacheConfig(cacheNames = "investigationInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class InvestigationInfoQueryService {

    @Autowired
    private InvestigationInfoRepository investigationInfoRepository;

    @Autowired
    private InvestigationInfoMapper investigationInfoMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(InvestigationInfoDTO investigationInfo, Pageable pageable){
        Page<InvestigationInfo> page = investigationInfoRepository.findAll(new Spec(investigationInfo),pageable);
        return PageUtil.toPage(page.map(investigationInfoMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(InvestigationInfoDTO investigationInfo){
        return investigationInfoMapper.toDto(investigationInfoRepository.findAll(new Spec(investigationInfo)));
    }

    class Spec implements Specification<InvestigationInfo> {

        private InvestigationInfoDTO investigationInfo;

        public Spec(InvestigationInfoDTO investigationInfo){
            this.investigationInfo = investigationInfo;
        }

        @Override
        public Predicate toPredicate(Root<InvestigationInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(investigationInfo.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+investigationInfo.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
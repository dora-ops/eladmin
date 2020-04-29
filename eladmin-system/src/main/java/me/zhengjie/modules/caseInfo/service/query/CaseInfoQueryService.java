package me.zhengjie.modules.caseInfo.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.caseInfo.domain.CaseInfo;
import me.zhengjie.modules.caseInfo.service.dto.CaseInfoDTO;
import me.zhengjie.modules.caseInfo.repository.CaseInfoRepository;
import me.zhengjie.modules.caseInfo.service.mapper.CaseInfoMapper;
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
@CacheConfig(cacheNames = "caseInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CaseInfoQueryService {

    @Autowired
    private CaseInfoRepository caseInfoRepository;

    @Autowired
    private CaseInfoMapper caseInfoMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CaseInfoDTO caseInfo, Pageable pageable){
        Page<CaseInfo> page = caseInfoRepository.findAll(new Spec(caseInfo),pageable);
        return PageUtil.toPage(page.map(caseInfoMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CaseInfoDTO caseInfo){
        return caseInfoMapper.toDto(caseInfoRepository.findAll(new Spec(caseInfo)));
    }

    class Spec implements Specification<CaseInfo> {

        private CaseInfoDTO caseInfo;

        public Spec(CaseInfoDTO caseInfo){
            this.caseInfo = caseInfo;
        }

        @Override
        public Predicate toPredicate(Root<CaseInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(caseInfo.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+caseInfo.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
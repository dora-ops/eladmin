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

            if(!ObjectUtils.isEmpty(investigationInfo.getKind())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("kind").as(String.class),investigationInfo.getKind()));
            }
            if(!ObjectUtils.isEmpty(investigationInfo.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+investigationInfo.getName()+"%"));
            }
            if(!ObjectUtils.isEmpty(investigationInfo.getUname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("uname").as(String.class),"%"+investigationInfo.getUname()+"%"));
            }
            if(!ObjectUtils.isEmpty(investigationInfo.getUid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("uid").as(Long.class),investigationInfo.getUid()));
            }
            if(!ObjectUtils.isEmpty(investigationInfo.getDealUid())){
                /**
                * 精确,
                */
                list.add(cb.equal(root.get("dealUid").as(String.class),investigationInfo.getDealUid()));
            }
            if(!ObjectUtils.isEmpty(investigationInfo.getContent())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("content").as(String.class),"%"+investigationInfo.getContent()+"%"));
            }
            if(!ObjectUtils.isEmpty(investigationInfo.getDealUname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("deal_uname").as(String.class),"%"+investigationInfo.getDealUname()+"%"));
            }
            if(!ObjectUtils.isEmpty(investigationInfo.getCid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("cid").as(Integer.class),investigationInfo.getCid()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
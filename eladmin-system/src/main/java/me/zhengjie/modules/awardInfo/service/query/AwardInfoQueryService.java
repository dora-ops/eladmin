package me.zhengjie.modules.awardInfo.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.awardInfo.domain.AwardInfo;
import me.zhengjie.modules.awardInfo.service.dto.AwardInfoDTO;
import me.zhengjie.modules.awardInfo.repository.AwardInfoRepository;
import me.zhengjie.modules.awardInfo.service.mapper.AwardInfoMapper;
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
@CacheConfig(cacheNames = "awardInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AwardInfoQueryService {

    @Autowired
    private AwardInfoRepository awardInfoRepository;

    @Autowired
    private AwardInfoMapper awardInfoMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AwardInfoDTO awardInfo, Pageable pageable){
        Page<AwardInfo> page = awardInfoRepository.findAll(new Spec(awardInfo),pageable);
        return PageUtil.toPage(page.map(awardInfoMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AwardInfoDTO awardInfo){
        return awardInfoMapper.toDto(awardInfoRepository.findAll(new Spec(awardInfo)));
    }

    class Spec implements Specification<AwardInfo> {

        private AwardInfoDTO awardInfo;

        public Spec(AwardInfoDTO awardInfo){
            this.awardInfo = awardInfo;
        }

        @Override
        public Predicate toPredicate(Root<AwardInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(awardInfo.getId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("id").as(Long.class),awardInfo.getId()));
            }
            if(!ObjectUtils.isEmpty(awardInfo.getCid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("cid").as(Long.class),awardInfo.getCid()));
            }
            if(!ObjectUtils.isEmpty(awardInfo.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+awardInfo.getName()+"%"));
            }
            if(!ObjectUtils.isEmpty(awardInfo.getUname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("uname").as(String.class),"%"+awardInfo.getUname()+"%"));
            }
            if(!ObjectUtils.isEmpty(awardInfo.getUid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("uid").as(Long.class),awardInfo.getUid()));
            }
            if(!ObjectUtils.isEmpty(awardInfo.getDealUid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("dealUid").as(String.class),awardInfo.getDealUid()));
            }
            if(!ObjectUtils.isEmpty(awardInfo.getContent())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("content").as(String.class),"%"+awardInfo.getContent()+"%"));
            }
            if(!ObjectUtils.isEmpty(awardInfo.getHandleUname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("handleUname").as(String.class),"%"+awardInfo.getHandleUname()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
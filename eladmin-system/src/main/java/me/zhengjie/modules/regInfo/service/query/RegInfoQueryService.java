package me.zhengjie.modules.regInfo.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.regInfo.domain.RegInfo;
import me.zhengjie.modules.regInfo.service.dto.RegInfoDTO;
import me.zhengjie.modules.regInfo.repository.RegInfoRepository;
import me.zhengjie.modules.regInfo.service.mapper.RegInfoMapper;
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
@CacheConfig(cacheNames = "regInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RegInfoQueryService {

    @Autowired
    private RegInfoRepository regInfoRepository;

    @Autowired
    private RegInfoMapper regInfoMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RegInfoDTO regInfo, Pageable pageable){
        Page<RegInfo> page = regInfoRepository.findAll(new Spec(regInfo),pageable);
        return PageUtil.toPage(page.map(regInfoMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RegInfoDTO regInfo){
        return regInfoMapper.toDto(regInfoRepository.findAll(new Spec(regInfo)));
    }

    class Spec implements Specification<RegInfo> {

        private RegInfoDTO regInfo;

        public Spec(RegInfoDTO regInfo){
            this.regInfo = regInfo;
        }

        @Override
        public Predicate toPredicate(Root<RegInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(regInfo.getKind())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("kind").as(String.class),regInfo.getKind()));
            }
            if(!ObjectUtils.isEmpty(regInfo.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+regInfo.getName()+"%"));
            }
            if(!ObjectUtils.isEmpty(regInfo.getImp())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("imp").as(String.class),regInfo.getImp()));
            }
            if(!ObjectUtils.isEmpty(regInfo.getSummary())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("summary").as(String.class),"%"+regInfo.getSummary()+"%"));
            }
            if(!ObjectUtils.isEmpty(regInfo.getUname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("uname").as(String.class),"%"+regInfo.getUname()+"%"));
            }
            if(!ObjectUtils.isEmpty(regInfo.getUid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("uid").as(Long.class),regInfo.getUid()));
            }
            if(!ObjectUtils.isEmpty(regInfo.getDealUid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("dealUid").as(String.class),regInfo.getDealUid()));
            }
            if(!ObjectUtils.isEmpty(regInfo.getProvider())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("provider").as(String.class),"%"+regInfo.getProvider()+"%"));
            }
            if(!ObjectUtils.isEmpty(regInfo.getDealUname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("deal_uname").as(String.class),"%"+regInfo.getDealUname()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
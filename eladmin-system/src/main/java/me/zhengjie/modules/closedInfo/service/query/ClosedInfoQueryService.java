package me.zhengjie.modules.closedInfo.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.closedInfo.domain.ClosedInfo;
import me.zhengjie.modules.closedInfo.service.dto.ClosedInfoDTO;
import me.zhengjie.modules.closedInfo.repository.ClosedInfoRepository;
import me.zhengjie.modules.closedInfo.service.mapper.ClosedInfoMapper;
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
@CacheConfig(cacheNames = "closedInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ClosedInfoQueryService {

    @Autowired
    private ClosedInfoRepository closedInfoRepository;

    @Autowired
    private ClosedInfoMapper closedInfoMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ClosedInfoDTO closedInfo, Pageable pageable){
        Page<ClosedInfo> page = closedInfoRepository.findAll(new Spec(closedInfo),pageable);
        return PageUtil.toPage(page.map(closedInfoMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ClosedInfoDTO closedInfo){
        return closedInfoMapper.toDto(closedInfoRepository.findAll(new Spec(closedInfo)));
    }

    class Spec implements Specification<ClosedInfo> {

        private ClosedInfoDTO closedInfo;

        public Spec(ClosedInfoDTO closedInfo){
            this.closedInfo = closedInfo;
        }

        @Override
        public Predicate toPredicate(Root<ClosedInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(closedInfo.getId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("id").as(Long.class),closedInfo.getId()));
            }
            if(!ObjectUtils.isEmpty(closedInfo.getCid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("cid").as(Long.class),closedInfo.getCid()));
            }
            if(!ObjectUtils.isEmpty(closedInfo.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+closedInfo.getName()+"%"));
            }
            if(!ObjectUtils.isEmpty(closedInfo.getUname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("uname").as(String.class),"%"+closedInfo.getUname()+"%"));
            }
            if(!ObjectUtils.isEmpty(closedInfo.getUid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("uid").as(Long.class),closedInfo.getUid()));
            }
            if(!ObjectUtils.isEmpty(closedInfo.getDealUid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("dealUid").as(String.class),closedInfo.getDealUid()));
            }
            if(!ObjectUtils.isEmpty(closedInfo.getContent())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("content").as(String.class),"%"+closedInfo.getContent()+"%"));
            }
            if(!ObjectUtils.isEmpty(closedInfo.getDealName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("dealName").as(String.class),"%"+closedInfo.getDealName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
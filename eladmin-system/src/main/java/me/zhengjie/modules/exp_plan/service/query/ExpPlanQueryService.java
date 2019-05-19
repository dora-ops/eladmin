package me.zhengjie.modules.exp_plan.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.exp_plan.domain.ExpPlan;
import me.zhengjie.modules.exp_plan.service.dto.ExpPlanDTO;
import me.zhengjie.modules.exp_plan.repository.ExpPlanRepository;
import me.zhengjie.modules.exp_plan.service.mapper.ExpPlanMapper;
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
@CacheConfig(cacheNames = "expPlan")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ExpPlanQueryService {

    @Autowired
    private ExpPlanRepository expPlanRepository;

    @Autowired
    private ExpPlanMapper expPlanMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExpPlanDTO expPlan, Pageable pageable){
        Page<ExpPlan> page = expPlanRepository.findAll(new Spec(expPlan),pageable);
        return PageUtil.toPage(page.map(expPlanMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExpPlanDTO expPlan){
        return expPlanMapper.toDto(expPlanRepository.findAll(new Spec(expPlan)));
    }

    class Spec implements Specification<ExpPlan> {

        private ExpPlanDTO expPlan;

        public Spec(ExpPlanDTO expPlan){
            this.expPlan = expPlan;
        }

        @Override
        public Predicate toPredicate(Root<ExpPlan> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(expPlan.getCourseName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("courseName").as(String.class),"%"+expPlan.getCourseName()+"%"));
            }
            if(!ObjectUtils.isEmpty(expPlan.getExpName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("expName").as(String.class),"%"+expPlan.getExpName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
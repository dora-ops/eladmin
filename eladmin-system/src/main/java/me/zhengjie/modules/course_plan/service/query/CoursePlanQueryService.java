package me.zhengjie.modules.course_plan.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.course_plan.domain.CoursePlan;
import me.zhengjie.modules.course_plan.service.dto.CoursePlanDTO;
import me.zhengjie.modules.course_plan.repository.CoursePlanRepository;
import me.zhengjie.modules.course_plan.service.mapper.CoursePlanMapper;
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
@CacheConfig(cacheNames = "coursePlan")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CoursePlanQueryService {

    @Autowired
    private CoursePlanRepository coursePlanRepository;

    @Autowired
    private CoursePlanMapper coursePlanMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CoursePlanDTO coursePlan, Pageable pageable){
        Page<CoursePlan> page = coursePlanRepository.findAll(new Spec(coursePlan),pageable);
        return PageUtil.toPage(page.map(coursePlanMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CoursePlanDTO coursePlan){
        return coursePlanMapper.toDto(coursePlanRepository.findAll(new Spec(coursePlan)));
    }

    class Spec implements Specification<CoursePlan> {

        private CoursePlanDTO coursePlan;

        public Spec(CoursePlanDTO coursePlan){
            this.coursePlan = coursePlan;
        }

        @Override
        public Predicate toPredicate(Root<CoursePlan> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(coursePlan.getCourseName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("courseName").as(String.class),"%"+coursePlan.getCourseName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
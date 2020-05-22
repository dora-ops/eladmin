package me.zhengjie.modules.course.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.course.domain.Course;
import me.zhengjie.modules.course.service.dto.CourseDTO;
import me.zhengjie.modules.course.repository.CourseRepository;
import me.zhengjie.modules.course.service.mapper.CourseMapper;
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
@CacheConfig(cacheNames = "course")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseQueryService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CourseDTO course, Pageable pageable){
        Page<Course> page = courseRepository.findAll(new Spec(course),pageable);
        return PageUtil.toPage(page.map(courseMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CourseDTO course){
        return courseMapper.toDto(courseRepository.findAll(new Spec(course)));
    }

    class Spec implements Specification<Course> {

        private CourseDTO course;

        public Spec(CourseDTO course){
            this.course = course;
        }

        @Override
        public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(course.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+course.getName()+"%"));
            }
            if(!ObjectUtils.isEmpty(course.getTeaId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("teaId").as(Long.class),course.getTeaId()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
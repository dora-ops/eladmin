package me.zhengjie.modules.stu_course.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.stu_course.domain.StuCourse;
import me.zhengjie.modules.stu_course.service.dto.StuCourseDTO;
import me.zhengjie.modules.stu_course.repository.StuCourseRepository;
import me.zhengjie.modules.stu_course.service.mapper.StuCourseMapper;
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
@CacheConfig(cacheNames = "stuCourse")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StuCourseQueryService {

    @Autowired
    private StuCourseRepository stuCourseRepository;

    @Autowired
    private StuCourseMapper stuCourseMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(StuCourseDTO stuCourse, Pageable pageable){
        Page<StuCourse> page = stuCourseRepository.findAll(new Spec(stuCourse),pageable);
        return PageUtil.toPage(page.map(stuCourseMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(StuCourseDTO stuCourse){
        return stuCourseMapper.toDto(stuCourseRepository.findAll(new Spec(stuCourse)));
    }

    class Spec implements Specification<StuCourse> {

        private StuCourseDTO stuCourse;

        public Spec(StuCourseDTO stuCourse){
            this.stuCourse = stuCourse;
        }

        @Override
        public Predicate toPredicate(Root<StuCourse> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(stuCourse.getExpName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("exp_name").as(String.class),"%"+stuCourse.getExpName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
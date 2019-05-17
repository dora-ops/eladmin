package me.zhengjie.modules.grade.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.grade.domain.Grade;
import me.zhengjie.modules.grade.service.dto.GradeDTO;
import me.zhengjie.modules.grade.repository.GradeRepository;
import me.zhengjie.modules.grade.service.mapper.GradeMapper;
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
@CacheConfig(cacheNames = "grade")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GradeQueryService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeMapper gradeMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(GradeDTO grade, Pageable pageable){
        Page<Grade> page = gradeRepository.findAll(new Spec(grade),pageable);
        return PageUtil.toPage(page.map(gradeMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(GradeDTO grade){
        return gradeMapper.toDto(gradeRepository.findAll(new Spec(grade)));
    }

    class Spec implements Specification<Grade> {

        private GradeDTO grade;

        public Spec(GradeDTO grade){
            this.grade = grade;
        }

        @Override
        public Predicate toPredicate(Root<Grade> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(grade.getTitle())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("title").as(String.class),"%"+grade.getTitle()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
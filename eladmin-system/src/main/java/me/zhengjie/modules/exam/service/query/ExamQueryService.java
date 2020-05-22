package me.zhengjie.modules.exam.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.exam.domain.Exam;
import me.zhengjie.modules.exam.service.dto.ExamDTO;
import me.zhengjie.modules.exam.repository.ExamRepository;
import me.zhengjie.modules.exam.service.mapper.ExamMapper;
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
@CacheConfig(cacheNames = "exam")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ExamQueryService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamMapper examMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExamDTO exam, Pageable pageable){
        Page<Exam> page = examRepository.findAll(new Spec(exam),pageable);
        return PageUtil.toPage(page.map(examMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExamDTO exam){
        return examMapper.toDto(examRepository.findAll(new Spec(exam)));
    }

    class Spec implements Specification<Exam> {

        private ExamDTO exam;

        public Spec(ExamDTO exam){
            this.exam = exam;
        }

        @Override
        public Predicate toPredicate(Root<Exam> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(exam.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+exam.getName()+"%"));
            }
            if(!ObjectUtils.isEmpty(exam.getType())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("type").as(String.class),exam.getType()));
            }
            if(!ObjectUtils.isEmpty(exam.getOkScore())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("okScore").as(String.class),exam.getOkScore()));
            }
            if(!ObjectUtils.isEmpty(exam.getCreateId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("createId").as(String.class),exam.getCreateId()));
            }
            if(!ObjectUtils.isEmpty(exam.getCreateName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("createName").as(String.class),"%"+exam.getCreateName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
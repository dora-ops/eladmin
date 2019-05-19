package me.zhengjie.modules.stu_job.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.stu_job.domain.StuJob;
import me.zhengjie.modules.stu_job.service.dto.StuJobDTO;
import me.zhengjie.modules.stu_job.repository.StuJobRepository;
import me.zhengjie.modules.stu_job.service.mapper.StuJobMapper;
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
@CacheConfig(cacheNames = "stuJob")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StuJobQueryService {

    @Autowired
    private StuJobRepository stuJobRepository;

    @Autowired
    private StuJobMapper stuJobMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(StuJobDTO stuJob, Pageable pageable){
        Page<StuJob> page = stuJobRepository.findAll(new Spec(stuJob),pageable);
        return PageUtil.toPage(page.map(stuJobMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(StuJobDTO stuJob){
        return stuJobMapper.toDto(stuJobRepository.findAll(new Spec(stuJob)));
    }

    class Spec implements Specification<StuJob> {

        private StuJobDTO stuJob;

        public Spec(StuJobDTO stuJob){
            this.stuJob = stuJob;
        }

        @Override
        public Predicate toPredicate(Root<StuJob> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(stuJob.getExpName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("expName").as(String.class),"%"+stuJob.getExpName()+"%"));
            }
            if(!ObjectUtils.isEmpty(stuJob.getStuName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("stuName").as(String.class),"%"+stuJob.getStuName()+"%"));
            }
            if(!ObjectUtils.isEmpty(stuJob.getStuNumber())){
                /**
                 * 模糊
                 */
                list.add(cb.equal(root.get("stuNumber").as(String.class),stuJob.getStuNumber()));
            }
            if(!ObjectUtils.isEmpty(stuJob.getTeaNumber())){
                /**
                 * 模糊
                 */
                list.add(cb.equal(root.get("teaNumber").as(String.class),stuJob.getTeaNumber()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
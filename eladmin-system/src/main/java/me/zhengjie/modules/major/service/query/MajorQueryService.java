package me.zhengjie.modules.major.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.major.domain.Major;
import me.zhengjie.modules.major.service.dto.MajorDTO;
import me.zhengjie.modules.major.repository.MajorRepository;
import me.zhengjie.modules.major.service.mapper.MajorMapper;
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
@CacheConfig(cacheNames = "major")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MajorQueryService {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private MajorMapper majorMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MajorDTO major, Pageable pageable){
        Page<Major> page = majorRepository.findAll(new Spec(major),pageable);
        return PageUtil.toPage(page.map(majorMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MajorDTO major){
        return majorMapper.toDto(majorRepository.findAll(new Spec(major)));
    }

    class Spec implements Specification<Major> {

        private MajorDTO major;

        public Spec(MajorDTO major){
            this.major = major;
        }

        @Override
        public Predicate toPredicate(Root<Major> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(major.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+major.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
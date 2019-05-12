package me.zhengjie.modules.medical.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.medical.domain.Medical;
import me.zhengjie.modules.medical.service.dto.MedicalDTO;
import me.zhengjie.modules.medical.repository.MedicalRepository;
import me.zhengjie.modules.medical.service.mapper.MedicalMapper;
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
@CacheConfig(cacheNames = "medical")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MedicalQueryService {

    @Autowired
    private MedicalRepository medicalRepository;

    @Autowired
    private MedicalMapper medicalMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MedicalDTO medical, Pageable pageable){
        Page<Medical> page = medicalRepository.findAll(new Spec(medical),pageable);
        return PageUtil.toPage(page.map(medicalMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MedicalDTO medical){
        return medicalMapper.toDto(medicalRepository.findAll(new Spec(medical)));
    }

    class Spec implements Specification<Medical> {

        private MedicalDTO medical;

        public Spec(MedicalDTO medical){
            this.medical = medical;
        }

        @Override
        public Predicate toPredicate(Root<Medical> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(medical.getTitle())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("title").as(String.class),"%"+medical.getTitle()+"%"));
            }
            if(!ObjectUtils.isEmpty(medical.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+medical.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
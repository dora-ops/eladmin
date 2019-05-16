package me.zhengjie.modules.experiment.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.experiment.domain.Experiment;
import me.zhengjie.modules.experiment.service.dto.ExperimentDTO;
import me.zhengjie.modules.experiment.repository.ExperimentRepository;
import me.zhengjie.modules.experiment.service.mapper.ExperimentMapper;
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
@CacheConfig(cacheNames = "experiment")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ExperimentQueryService {

    @Autowired
    private ExperimentRepository experimentRepository;

    @Autowired
    private ExperimentMapper experimentMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExperimentDTO experiment, Pageable pageable){
        Page<Experiment> page = experimentRepository.findAll(new Spec(experiment),pageable);
        return PageUtil.toPage(page.map(experimentMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExperimentDTO experiment){
        return experimentMapper.toDto(experimentRepository.findAll(new Spec(experiment)));
    }

    class Spec implements Specification<Experiment> {

        private ExperimentDTO experiment;

        public Spec(ExperimentDTO experiment){
            this.experiment = experiment;
        }

        @Override
        public Predicate toPredicate(Root<Experiment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(experiment.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+experiment.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
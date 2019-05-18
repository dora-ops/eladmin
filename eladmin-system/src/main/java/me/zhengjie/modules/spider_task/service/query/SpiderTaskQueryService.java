package me.zhengjie.modules.spider_task.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.spider_task.domain.SpiderTask;
import me.zhengjie.modules.spider_task.service.dto.SpiderTaskDTO;
import me.zhengjie.modules.spider_task.repository.SpiderTaskRepository;
import me.zhengjie.modules.spider_task.service.mapper.SpiderTaskMapper;
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
@CacheConfig(cacheNames = "spiderTask")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SpiderTaskQueryService {

    @Autowired
    private SpiderTaskRepository spiderTaskRepository;

    @Autowired
    private SpiderTaskMapper spiderTaskMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SpiderTaskDTO spiderTask, Pageable pageable){
        Page<SpiderTask> page = spiderTaskRepository.findAll(new Spec(spiderTask),pageable);
        return PageUtil.toPage(page.map(spiderTaskMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SpiderTaskDTO spiderTask){
        return spiderTaskMapper.toDto(spiderTaskRepository.findAll(new Spec(spiderTask)));
    }

    class Spec implements Specification<SpiderTask> {

        private SpiderTaskDTO spiderTask;

        public Spec(SpiderTaskDTO spiderTask){
            this.spiderTask = spiderTask;
        }

        @Override
        public Predicate toPredicate(Root<SpiderTask> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
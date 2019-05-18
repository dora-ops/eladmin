package me.zhengjie.modules.spider_log.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.spider_log.domain.SpiderLog;
import me.zhengjie.modules.spider_log.service.dto.SpiderLogDTO;
import me.zhengjie.modules.spider_log.repository.SpiderLogRepository;
import me.zhengjie.modules.spider_log.service.mapper.SpiderLogMapper;
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
@CacheConfig(cacheNames = "spiderLog")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SpiderLogQueryService {

    @Autowired
    private SpiderLogRepository spiderLogRepository;

    @Autowired
    private SpiderLogMapper spiderLogMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SpiderLogDTO spiderLog, Pageable pageable){
        Page<SpiderLog> page = spiderLogRepository.findAll(new Spec(spiderLog),pageable);
        return PageUtil.toPage(page.map(spiderLogMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SpiderLogDTO spiderLog){
        return spiderLogMapper.toDto(spiderLogRepository.findAll(new Spec(spiderLog)));
    }

    class Spec implements Specification<SpiderLog> {

        private SpiderLogDTO spiderLog;

        public Spec(SpiderLogDTO spiderLog){
            this.spiderLog = spiderLog;
        }

        @Override
        public Predicate toPredicate(Root<SpiderLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(spiderLog.getUrl())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("url").as(String.class),"%"+spiderLog.getUrl()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
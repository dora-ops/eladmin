package me.zhengjie.modules.topic.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.topic.domain.Topic;
import me.zhengjie.modules.topic.service.dto.TopicDTO;
import me.zhengjie.modules.topic.repository.TopicRepository;
import me.zhengjie.modules.topic.service.mapper.TopicMapper;
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
@CacheConfig(cacheNames = "topic")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TopicQueryService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicMapper topicMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TopicDTO topic, Pageable pageable){
        Page<Topic> page = topicRepository.findAll(new Spec(topic),pageable);
        return PageUtil.toPage(page.map(topicMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TopicDTO topic){
        return topicMapper.toDto(topicRepository.findAll(new Spec(topic)));
    }

    class Spec implements Specification<Topic> {

        private TopicDTO topic;

        public Spec(TopicDTO topic){
            this.topic = topic;
        }

        @Override
        public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(topic.getTitle())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("title").as(String.class),"%"+topic.getTitle()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}
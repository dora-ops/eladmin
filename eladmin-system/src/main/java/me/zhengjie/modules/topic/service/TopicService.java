package me.zhengjie.modules.topic.service;

import me.zhengjie.modules.topic.domain.Topic;
import me.zhengjie.modules.topic.service.dto.TopicDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-12
*/
@CacheConfig(cacheNames = "topic")
public interface TopicService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TopicDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TopicDTO create(Topic resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Topic resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
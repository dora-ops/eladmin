package me.zhengjie.modules.pro_topic.service;

import me.zhengjie.modules.pro_topic.domain.ProTopic;
import me.zhengjie.modules.pro_topic.service.dto.ProTopicDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-12
*/
@CacheConfig(cacheNames = "proTopic")
public interface ProTopicService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ProTopicDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ProTopicDTO create(ProTopic resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(ProTopic resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
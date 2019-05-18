package me.zhengjie.modules.spider_task.service;

import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import me.zhengjie.modules.spider_task.domain.SpiderTask;
import me.zhengjie.modules.spider_task.service.dto.SpiderTaskDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-18
*/
@CacheConfig(cacheNames = "spiderTask")
public interface SpiderTaskService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    SpiderTaskDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    SpiderTaskDTO create(SpiderTask resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(SpiderTask resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    Object start(String code,SpiderTask resources);
}
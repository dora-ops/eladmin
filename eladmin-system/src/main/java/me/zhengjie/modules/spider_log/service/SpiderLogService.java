package me.zhengjie.modules.spider_log.service;

import me.zhengjie.modules.spider_log.domain.SpiderLog;
import me.zhengjie.modules.spider_log.service.dto.SpiderLogDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-19
*/
@CacheConfig(cacheNames = "spiderLog")
public interface SpiderLogService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    SpiderLogDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    SpiderLogDTO create(SpiderLog resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(SpiderLog resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
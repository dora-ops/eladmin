package me.zhengjie.modules.spider.service;

import me.zhengjie.modules.spider.domain.Spider;
import me.zhengjie.modules.spider.service.dto.SpiderDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-19
*/
@CacheConfig(cacheNames = "spider")
public interface SpiderService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    SpiderDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    SpiderDTO create(Spider resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Spider resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
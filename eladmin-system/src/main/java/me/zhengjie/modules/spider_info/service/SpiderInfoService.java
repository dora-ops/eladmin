package me.zhengjie.modules.spider_info.service;

import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import me.zhengjie.modules.spider_info.service.dto.SpiderInfoDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-18
*/
@CacheConfig(cacheNames = "spiderInfo")
public interface SpiderInfoService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    SpiderInfoDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    SpiderInfoDTO create(SpiderInfo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(SpiderInfo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);


}
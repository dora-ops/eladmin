package me.zhengjie.modules.handleInfo.service;

import me.zhengjie.modules.handleInfo.domain.HandleInfo;
import me.zhengjie.modules.handleInfo.service.dto.HandleInfoDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-04-30
*/
@CacheConfig(cacheNames = "handleInfo")
public interface HandleInfoService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    HandleInfoDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    HandleInfoDTO create(HandleInfo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(HandleInfo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
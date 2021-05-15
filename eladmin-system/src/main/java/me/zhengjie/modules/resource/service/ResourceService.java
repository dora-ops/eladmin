package me.zhengjie.modules.resource.service;

import me.zhengjie.modules.resource.domain.Resource;
import me.zhengjie.modules.resource.service.dto.ResourceDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author y
* @date 2021-05-15
*/
@CacheConfig(cacheNames = "resource")
public interface ResourceService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ResourceDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ResourceDTO create(Resource resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Resource resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
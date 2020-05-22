package me.zhengjie.modules.tea_resource.service;

import me.zhengjie.modules.tea_resource.domain.TeaResource;
import me.zhengjie.modules.tea_resource.service.dto.TeaResourceDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-05-15
*/
@CacheConfig(cacheNames = "teaResource")
public interface TeaResourceService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TeaResourceDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TeaResourceDTO create(TeaResource resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(TeaResource resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
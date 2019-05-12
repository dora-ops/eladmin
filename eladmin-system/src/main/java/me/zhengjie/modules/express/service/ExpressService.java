package me.zhengjie.modules.express.service;

import me.zhengjie.modules.express.domain.Express;
import me.zhengjie.modules.express.service.dto.ExpressDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-12
*/
@CacheConfig(cacheNames = "express")
public interface ExpressService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ExpressDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ExpressDTO create(Express resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Express resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
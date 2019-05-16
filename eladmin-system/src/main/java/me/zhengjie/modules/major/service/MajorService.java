package me.zhengjie.modules.major.service;

import me.zhengjie.modules.major.domain.Major;
import me.zhengjie.modules.major.service.dto.MajorDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-16
*/
@CacheConfig(cacheNames = "major")
public interface MajorService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    MajorDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    MajorDTO create(Major resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Major resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
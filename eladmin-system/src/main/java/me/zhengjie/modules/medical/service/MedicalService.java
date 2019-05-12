package me.zhengjie.modules.medical.service;

import me.zhengjie.modules.medical.domain.Medical;
import me.zhengjie.modules.medical.service.dto.MedicalDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-12
*/
@CacheConfig(cacheNames = "medical")
public interface MedicalService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    MedicalDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    MedicalDTO create(Medical resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Medical resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
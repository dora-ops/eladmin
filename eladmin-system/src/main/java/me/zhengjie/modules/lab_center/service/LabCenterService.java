package me.zhengjie.modules.lab_center.service;

import me.zhengjie.modules.lab_center.domain.LabCenter;
import me.zhengjie.modules.lab_center.service.dto.LabCenterDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-16
*/
@CacheConfig(cacheNames = "labCenter")
public interface LabCenterService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    LabCenterDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    LabCenterDTO create(LabCenter resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(LabCenter resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
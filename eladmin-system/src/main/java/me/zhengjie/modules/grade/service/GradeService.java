package me.zhengjie.modules.grade.service;

import me.zhengjie.modules.grade.domain.Grade;
import me.zhengjie.modules.grade.service.dto.GradeDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-18
*/
@CacheConfig(cacheNames = "grade")
public interface GradeService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    GradeDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    GradeDTO create(Grade resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Grade resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
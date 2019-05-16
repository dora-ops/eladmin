package me.zhengjie.modules.course_plan.service;

import me.zhengjie.modules.course_plan.domain.CoursePlan;
import me.zhengjie.modules.course_plan.service.dto.CoursePlanDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-16
*/
@CacheConfig(cacheNames = "coursePlan")
public interface CoursePlanService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CoursePlanDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CoursePlanDTO create(CoursePlan resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(CoursePlan resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
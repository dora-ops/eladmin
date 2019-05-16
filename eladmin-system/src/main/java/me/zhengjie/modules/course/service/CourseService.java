package me.zhengjie.modules.course.service;

import me.zhengjie.modules.course.domain.Course;
import me.zhengjie.modules.course.service.dto.CourseDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-16
*/
@CacheConfig(cacheNames = "course")
public interface CourseService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CourseDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CourseDTO create(Course resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Course resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
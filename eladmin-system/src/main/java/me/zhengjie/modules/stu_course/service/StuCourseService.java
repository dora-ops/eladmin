package me.zhengjie.modules.stu_course.service;

import me.zhengjie.modules.stu_course.domain.StuCourse;
import me.zhengjie.modules.stu_course.service.dto.StuCourseDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-16
*/
@CacheConfig(cacheNames = "stuCourse")
public interface StuCourseService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    StuCourseDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    StuCourseDTO create(StuCourse resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(StuCourse resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
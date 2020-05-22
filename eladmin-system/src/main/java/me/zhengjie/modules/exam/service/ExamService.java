package me.zhengjie.modules.exam.service;

import me.zhengjie.modules.exam.domain.Exam;
import me.zhengjie.modules.exam.service.dto.ExamDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-05-15
*/
@CacheConfig(cacheNames = "exam")
public interface ExamService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ExamDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ExamDTO create(Exam resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Exam resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
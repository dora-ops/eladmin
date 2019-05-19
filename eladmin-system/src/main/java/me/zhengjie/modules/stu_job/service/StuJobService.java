package me.zhengjie.modules.stu_job.service;

import me.zhengjie.modules.stu_job.domain.StuJob;
import me.zhengjie.modules.stu_job.service.dto.StuJobDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-19
*/
@CacheConfig(cacheNames = "stuJob")
public interface StuJobService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    StuJobDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    StuJobDTO create(StuJob resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(StuJob resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
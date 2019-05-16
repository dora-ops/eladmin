package me.zhengjie.modules.experiment.service;

import me.zhengjie.modules.experiment.domain.Experiment;
import me.zhengjie.modules.experiment.service.dto.ExperimentDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-16
*/
@CacheConfig(cacheNames = "experiment")
public interface ExperimentService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ExperimentDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ExperimentDTO create(Experiment resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Experiment resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
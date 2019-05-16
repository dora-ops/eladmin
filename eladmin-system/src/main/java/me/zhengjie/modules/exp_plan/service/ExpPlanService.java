package me.zhengjie.modules.exp_plan.service;

import me.zhengjie.modules.exp_plan.domain.ExpPlan;
import me.zhengjie.modules.exp_plan.service.dto.ExpPlanDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-16
*/
@CacheConfig(cacheNames = "expPlan")
public interface ExpPlanService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ExpPlanDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ExpPlanDTO create(ExpPlan resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(ExpPlan resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
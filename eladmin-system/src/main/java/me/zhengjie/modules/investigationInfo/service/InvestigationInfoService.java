package me.zhengjie.modules.investigationInfo.service;

import me.zhengjie.modules.investigationInfo.domain.InvestigationInfo;
import me.zhengjie.modules.investigationInfo.service.dto.InvestigationInfoDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-04-30
*/
@CacheConfig(cacheNames = "investigationInfo")
public interface InvestigationInfoService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    InvestigationInfoDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    InvestigationInfoDTO create(InvestigationInfo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(InvestigationInfo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
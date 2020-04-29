package me.zhengjie.modules.caseInfo.service;

import me.zhengjie.modules.caseInfo.domain.CaseInfo;
import me.zhengjie.modules.caseInfo.service.dto.CaseInfoDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-04-29
*/
@CacheConfig(cacheNames = "caseInfo")
public interface CaseInfoService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CaseInfoDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CaseInfoDTO create(CaseInfo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(CaseInfo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
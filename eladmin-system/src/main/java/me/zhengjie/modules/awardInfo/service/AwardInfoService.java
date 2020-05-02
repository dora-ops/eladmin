package me.zhengjie.modules.awardInfo.service;

import me.zhengjie.modules.awardInfo.domain.AwardInfo;
import me.zhengjie.modules.awardInfo.service.dto.AwardInfoDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-05-02
*/
@CacheConfig(cacheNames = "awardInfo")
public interface AwardInfoService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    AwardInfoDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    AwardInfoDTO create(AwardInfo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(AwardInfo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
package me.zhengjie.modules.closedInfo.service;

import me.zhengjie.modules.closedInfo.domain.ClosedInfo;
import me.zhengjie.modules.closedInfo.service.dto.ClosedInfoDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-05-02
*/
@CacheConfig(cacheNames = "closedInfo")
public interface ClosedInfoService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ClosedInfoDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ClosedInfoDTO create(ClosedInfo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(ClosedInfo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
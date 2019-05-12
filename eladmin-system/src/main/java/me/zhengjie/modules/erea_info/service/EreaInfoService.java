package me.zhengjie.modules.erea_info.service;

import me.zhengjie.modules.erea_info.domain.EreaInfo;
import me.zhengjie.modules.erea_info.service.dto.EreaInfoDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-12
*/
@CacheConfig(cacheNames = "ereaInfo")
public interface EreaInfoService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    EreaInfoDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    EreaInfoDTO create(EreaInfo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(EreaInfo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
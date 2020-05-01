package me.zhengjie.modules.handleNotice.service;

import me.zhengjie.modules.handleNotice.domain.HandleNotice;
import me.zhengjie.modules.handleNotice.service.dto.HandleNoticeDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-04-30
*/
@CacheConfig(cacheNames = "handleNotice")
public interface HandleNoticeService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    HandleNoticeDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    HandleNoticeDTO create(HandleNotice resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(HandleNotice resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
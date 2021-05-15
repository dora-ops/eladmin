package me.zhengjie.modules.pubContent.service;

import me.zhengjie.modules.pubContent.domain.PubContent;
import me.zhengjie.modules.pubContent.service.dto.PubContentDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author y
* @date 2021-05-15
*/
@CacheConfig(cacheNames = "pubContent")
public interface PubContentService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    PubContentDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    PubContentDTO create(PubContent resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(PubContent resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
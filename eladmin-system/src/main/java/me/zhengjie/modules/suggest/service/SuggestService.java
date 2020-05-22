package me.zhengjie.modules.suggest.service;

import me.zhengjie.modules.suggest.domain.Suggest;
import me.zhengjie.modules.suggest.service.dto.SuggestDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-05-21
*/
@CacheConfig(cacheNames = "suggest")
public interface SuggestService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    SuggestDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    SuggestDTO create(Suggest resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Suggest resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
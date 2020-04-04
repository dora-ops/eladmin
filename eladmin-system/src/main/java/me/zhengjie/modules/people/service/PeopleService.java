package me.zhengjie.modules.people.service;

import me.zhengjie.modules.people.domain.People;
import me.zhengjie.modules.people.service.dto.PeopleDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-04-04
*/
@CacheConfig(cacheNames = "people")
public interface PeopleService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    PeopleDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    PeopleDTO create(People resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(People resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
package me.zhengjie.modules.person.service;

import me.zhengjie.modules.person.domain.Person;
import me.zhengjie.modules.person.service.dto.PersonDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author y
* @date 2021-05-15
*/
@CacheConfig(cacheNames = "person")
public interface PersonService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    PersonDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    PersonDTO create(Person resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Person resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
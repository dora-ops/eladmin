package me.zhengjie.modules.car.service;

import me.zhengjie.modules.car.domain.Car;
import me.zhengjie.modules.car.service.dto.CarDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-04-04
*/
@CacheConfig(cacheNames = "car")
public interface CarService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CarDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CarDTO create(Car resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Car resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
package me.zhengjie.modules.order.service;

import me.zhengjie.modules.order.domain.Order;
import me.zhengjie.modules.order.service.dto.OrderDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-04-04
*/
@CacheConfig(cacheNames = "order")
public interface OrderService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    OrderDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    OrderDTO create(Order resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Order resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
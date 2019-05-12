package me.zhengjie.modules.goods.service;

import me.zhengjie.modules.goods.domain.Goods;
import me.zhengjie.modules.goods.service.dto.GoodsDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-12
*/
@CacheConfig(cacheNames = "goods")
public interface GoodsService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    GoodsDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    GoodsDTO create(Goods resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Goods resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
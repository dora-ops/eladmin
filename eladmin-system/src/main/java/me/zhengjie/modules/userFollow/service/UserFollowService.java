package me.zhengjie.modules.userFollow.service;

import me.zhengjie.modules.userFollow.domain.UserFollow;
import me.zhengjie.modules.userFollow.service.dto.UserFollowDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author y
* @date 2021-05-15
*/
@CacheConfig(cacheNames = "userFollow")
public interface UserFollowService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    UserFollowDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    UserFollowDTO create(UserFollow resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(UserFollow resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
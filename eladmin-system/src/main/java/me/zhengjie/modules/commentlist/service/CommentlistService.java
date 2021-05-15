package me.zhengjie.modules.commentlist.service;

import me.zhengjie.modules.commentlist.domain.Commentlist;
import me.zhengjie.modules.commentlist.service.dto.CommentlistDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author y
* @date 2021-05-15
*/
@CacheConfig(cacheNames = "commentlist")
public interface CommentlistService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CommentlistDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CommentlistDTO create(Commentlist resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Commentlist resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
package me.zhengjie.modules.reply.service;

import me.zhengjie.modules.reply.domain.Reply;
import me.zhengjie.modules.reply.service.dto.ReplyDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-05-21
*/
@CacheConfig(cacheNames = "reply")
public interface ReplyService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ReplyDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ReplyDTO create(Reply resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Reply resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
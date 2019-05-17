package me.zhengjie.modules.notice.service;

import me.zhengjie.modules.notice.domain.Notice;
import me.zhengjie.modules.notice.service.dto.NoticeDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-18
*/
@CacheConfig(cacheNames = "notice")
public interface NoticeService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    NoticeDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    NoticeDTO create(Notice resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Notice resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
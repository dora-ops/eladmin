package me.zhengjie.modules.article.service;

import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.modules.article.service.dto.ArticleDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-17
*/
@CacheConfig(cacheNames = "article")
public interface ArticleService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ArticleDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ArticleDTO create(Article resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Article resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
package me.zhengjie.modules.projectInfo.service;

import me.zhengjie.modules.projectInfo.domain.ProjectInfo;
import me.zhengjie.modules.projectInfo.service.dto.ProjectInfoDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2020-05-02
*/
@CacheConfig(cacheNames = "projectInfo")
public interface ProjectInfoService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ProjectInfoDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ProjectInfoDTO create(ProjectInfo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(ProjectInfo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
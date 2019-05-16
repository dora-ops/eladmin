package me.zhengjie.modules.lab_room.service;

import me.zhengjie.modules.lab_room.domain.LabRoom;
import me.zhengjie.modules.lab_room.service.dto.LabRoomDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-16
*/
@CacheConfig(cacheNames = "labRoom")
public interface LabRoomService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    LabRoomDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    LabRoomDTO create(LabRoom resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(LabRoom resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}
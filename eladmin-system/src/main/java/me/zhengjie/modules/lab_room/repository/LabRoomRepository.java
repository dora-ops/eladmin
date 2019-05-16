package me.zhengjie.modules.lab_room.repository;

import me.zhengjie.modules.lab_room.domain.LabRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-16
*/
public interface LabRoomRepository extends JpaRepository<LabRoom, Long>, JpaSpecificationExecutor {
}
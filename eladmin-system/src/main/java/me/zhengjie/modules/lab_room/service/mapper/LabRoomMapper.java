package me.zhengjie.modules.lab_room.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.lab_room.domain.LabRoom;
import me.zhengjie.modules.lab_room.service.dto.LabRoomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LabRoomMapper extends EntityMapper<LabRoomDTO, LabRoom> {

}
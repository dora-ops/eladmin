package me.zhengjie.modules.lab_center.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.lab_center.domain.LabCenter;
import me.zhengjie.modules.lab_center.service.dto.LabCenterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LabCenterMapper extends EntityMapper<LabCenterDTO, LabCenter> {

}
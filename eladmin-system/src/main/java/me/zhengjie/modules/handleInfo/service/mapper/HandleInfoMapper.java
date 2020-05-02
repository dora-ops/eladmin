package me.zhengjie.modules.handleInfo.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.handleInfo.domain.HandleInfo;
import me.zhengjie.modules.handleInfo.service.dto.HandleInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-05-02
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HandleInfoMapper extends EntityMapper<HandleInfoDTO, HandleInfo> {

}
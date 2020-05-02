package me.zhengjie.modules.closedInfo.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.closedInfo.domain.ClosedInfo;
import me.zhengjie.modules.closedInfo.service.dto.ClosedInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-05-02
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClosedInfoMapper extends EntityMapper<ClosedInfoDTO, ClosedInfo> {

}
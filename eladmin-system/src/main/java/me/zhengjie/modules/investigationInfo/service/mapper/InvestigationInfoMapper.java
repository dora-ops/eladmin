package me.zhengjie.modules.investigationInfo.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.investigationInfo.domain.InvestigationInfo;
import me.zhengjie.modules.investigationInfo.service.dto.InvestigationInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-05-02
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvestigationInfoMapper extends EntityMapper<InvestigationInfoDTO, InvestigationInfo> {

}
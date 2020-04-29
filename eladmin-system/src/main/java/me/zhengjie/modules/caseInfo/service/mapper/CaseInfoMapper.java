package me.zhengjie.modules.caseInfo.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.caseInfo.domain.CaseInfo;
import me.zhengjie.modules.caseInfo.service.dto.CaseInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-04-29
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CaseInfoMapper extends EntityMapper<CaseInfoDTO, CaseInfo> {

}
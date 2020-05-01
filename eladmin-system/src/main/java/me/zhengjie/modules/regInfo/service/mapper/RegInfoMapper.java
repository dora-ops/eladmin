package me.zhengjie.modules.regInfo.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.regInfo.domain.RegInfo;
import me.zhengjie.modules.regInfo.service.dto.RegInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-04-30
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegInfoMapper extends EntityMapper<RegInfoDTO, RegInfo> {

}
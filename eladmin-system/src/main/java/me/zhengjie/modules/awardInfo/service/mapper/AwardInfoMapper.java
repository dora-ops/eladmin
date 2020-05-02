package me.zhengjie.modules.awardInfo.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.awardInfo.domain.AwardInfo;
import me.zhengjie.modules.awardInfo.service.dto.AwardInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-05-02
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AwardInfoMapper extends EntityMapper<AwardInfoDTO, AwardInfo> {

}
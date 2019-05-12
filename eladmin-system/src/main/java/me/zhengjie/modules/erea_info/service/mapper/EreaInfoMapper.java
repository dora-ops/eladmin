package me.zhengjie.modules.erea_info.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.erea_info.domain.EreaInfo;
import me.zhengjie.modules.erea_info.service.dto.EreaInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EreaInfoMapper extends EntityMapper<EreaInfoDTO, EreaInfo> {

}
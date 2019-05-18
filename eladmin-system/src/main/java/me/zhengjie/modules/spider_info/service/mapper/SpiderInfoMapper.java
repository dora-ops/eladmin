package me.zhengjie.modules.spider_info.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import me.zhengjie.modules.spider_info.service.dto.SpiderInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-18
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpiderInfoMapper extends EntityMapper<SpiderInfoDTO, SpiderInfo> {

}
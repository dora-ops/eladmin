package me.zhengjie.modules.spider_log.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.spider_log.domain.SpiderLog;
import me.zhengjie.modules.spider_log.service.dto.SpiderLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-19
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpiderLogMapper extends EntityMapper<SpiderLogDTO, SpiderLog> {

}
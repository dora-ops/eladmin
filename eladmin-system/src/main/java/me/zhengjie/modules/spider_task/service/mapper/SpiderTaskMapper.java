package me.zhengjie.modules.spider_task.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.spider_task.domain.SpiderTask;
import me.zhengjie.modules.spider_task.service.dto.SpiderTaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-18
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpiderTaskMapper extends EntityMapper<SpiderTaskDTO, SpiderTask> {

}
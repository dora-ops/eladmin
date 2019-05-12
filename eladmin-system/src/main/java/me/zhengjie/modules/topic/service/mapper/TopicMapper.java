package me.zhengjie.modules.topic.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.topic.domain.Topic;
import me.zhengjie.modules.topic.service.dto.TopicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TopicMapper extends EntityMapper<TopicDTO, Topic> {

}
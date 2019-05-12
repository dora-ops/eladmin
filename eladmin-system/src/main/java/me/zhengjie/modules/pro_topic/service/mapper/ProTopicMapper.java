package me.zhengjie.modules.pro_topic.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.pro_topic.domain.ProTopic;
import me.zhengjie.modules.pro_topic.service.dto.ProTopicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProTopicMapper extends EntityMapper<ProTopicDTO, ProTopic> {

}
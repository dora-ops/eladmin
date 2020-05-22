package me.zhengjie.modules.reply.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.reply.domain.Reply;
import me.zhengjie.modules.reply.service.dto.ReplyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-05-21
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyMapper extends EntityMapper<ReplyDTO, Reply> {

}
package me.zhengjie.modules.notice.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.notice.domain.Notice;
import me.zhengjie.modules.notice.service.dto.NoticeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-18
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeMapper extends EntityMapper<NoticeDTO, Notice> {

}
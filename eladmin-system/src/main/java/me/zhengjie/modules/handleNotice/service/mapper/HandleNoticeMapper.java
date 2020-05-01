package me.zhengjie.modules.handleNotice.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.handleNotice.domain.HandleNotice;
import me.zhengjie.modules.handleNotice.service.dto.HandleNoticeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-04-30
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HandleNoticeMapper extends EntityMapper<HandleNoticeDTO, HandleNotice> {

}
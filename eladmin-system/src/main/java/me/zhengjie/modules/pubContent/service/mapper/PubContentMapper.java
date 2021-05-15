package me.zhengjie.modules.pubContent.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.pubContent.domain.PubContent;
import me.zhengjie.modules.pubContent.service.dto.PubContentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author y
* @date 2021-05-15
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PubContentMapper extends EntityMapper<PubContentDTO, PubContent> {

}
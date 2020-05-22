package me.zhengjie.modules.suggest.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.suggest.domain.Suggest;
import me.zhengjie.modules.suggest.service.dto.SuggestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-05-21
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuggestMapper extends EntityMapper<SuggestDTO, Suggest> {

}
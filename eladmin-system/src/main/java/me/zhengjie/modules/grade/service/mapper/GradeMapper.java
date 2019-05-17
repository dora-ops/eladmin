package me.zhengjie.modules.grade.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.grade.domain.Grade;
import me.zhengjie.modules.grade.service.dto.GradeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-18
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GradeMapper extends EntityMapper<GradeDTO, Grade> {

}
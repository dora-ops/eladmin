package me.zhengjie.modules.course_plan.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.course_plan.domain.CoursePlan;
import me.zhengjie.modules.course_plan.service.dto.CoursePlanDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoursePlanMapper extends EntityMapper<CoursePlanDTO, CoursePlan> {

}
package me.zhengjie.modules.course.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.course.domain.Course;
import me.zhengjie.modules.course.service.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {

}
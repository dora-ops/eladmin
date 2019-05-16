package me.zhengjie.modules.stu_course.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.stu_course.domain.StuCourse;
import me.zhengjie.modules.stu_course.service.dto.StuCourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StuCourseMapper extends EntityMapper<StuCourseDTO, StuCourse> {

}
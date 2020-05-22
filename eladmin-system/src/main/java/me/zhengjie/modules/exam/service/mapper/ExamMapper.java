package me.zhengjie.modules.exam.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.exam.domain.Exam;
import me.zhengjie.modules.exam.service.dto.ExamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-05-15
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExamMapper extends EntityMapper<ExamDTO, Exam> {

}
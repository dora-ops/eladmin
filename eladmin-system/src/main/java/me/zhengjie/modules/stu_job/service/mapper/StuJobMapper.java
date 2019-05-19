package me.zhengjie.modules.stu_job.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.stu_job.domain.StuJob;
import me.zhengjie.modules.stu_job.service.dto.StuJobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-19
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StuJobMapper extends EntityMapper<StuJobDTO, StuJob> {

}
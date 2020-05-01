package me.zhengjie.modules.projectInfo.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.projectInfo.domain.ProjectInfo;
import me.zhengjie.modules.projectInfo.service.dto.ProjectInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-04-30
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectInfoMapper extends EntityMapper<ProjectInfoDTO, ProjectInfo> {

}
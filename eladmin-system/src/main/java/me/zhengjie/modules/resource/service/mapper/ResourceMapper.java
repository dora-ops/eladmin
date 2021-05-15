package me.zhengjie.modules.resource.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.resource.domain.Resource;
import me.zhengjie.modules.resource.service.dto.ResourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author y
* @date 2021-05-15
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper extends EntityMapper<ResourceDTO, Resource> {

}
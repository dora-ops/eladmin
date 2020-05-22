package me.zhengjie.modules.tea_resource.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.tea_resource.domain.TeaResource;
import me.zhengjie.modules.tea_resource.service.dto.TeaResourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-05-15
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeaResourceMapper extends EntityMapper<TeaResourceDTO, TeaResource> {

}
package me.zhengjie.modules.express.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.express.domain.Express;
import me.zhengjie.modules.express.service.dto.ExpressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpressMapper extends EntityMapper<ExpressDTO, Express> {

}
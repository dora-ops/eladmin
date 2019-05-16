package me.zhengjie.modules.major.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.major.domain.Major;
import me.zhengjie.modules.major.service.dto.MajorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MajorMapper extends EntityMapper<MajorDTO, Major> {

}
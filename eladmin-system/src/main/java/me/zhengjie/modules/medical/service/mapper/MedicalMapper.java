package me.zhengjie.modules.medical.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.medical.domain.Medical;
import me.zhengjie.modules.medical.service.dto.MedicalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicalMapper extends EntityMapper<MedicalDTO, Medical> {

}
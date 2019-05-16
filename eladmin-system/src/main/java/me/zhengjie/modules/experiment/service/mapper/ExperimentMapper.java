package me.zhengjie.modules.experiment.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.experiment.domain.Experiment;
import me.zhengjie.modules.experiment.service.dto.ExperimentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExperimentMapper extends EntityMapper<ExperimentDTO, Experiment> {

}
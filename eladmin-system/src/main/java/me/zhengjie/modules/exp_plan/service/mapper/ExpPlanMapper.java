package me.zhengjie.modules.exp_plan.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.exp_plan.domain.ExpPlan;
import me.zhengjie.modules.exp_plan.service.dto.ExpPlanDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpPlanMapper extends EntityMapper<ExpPlanDTO, ExpPlan> {

}
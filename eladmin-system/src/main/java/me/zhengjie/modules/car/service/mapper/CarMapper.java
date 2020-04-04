package me.zhengjie.modules.car.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.car.domain.Car;
import me.zhengjie.modules.car.service.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-04-04
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper extends EntityMapper<CarDTO, Car> {

}
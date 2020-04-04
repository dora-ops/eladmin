package me.zhengjie.modules.order.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.order.domain.Order;
import me.zhengjie.modules.order.service.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-04-04
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {

}
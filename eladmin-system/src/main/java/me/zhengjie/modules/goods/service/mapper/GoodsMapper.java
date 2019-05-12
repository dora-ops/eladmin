package me.zhengjie.modules.goods.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.goods.domain.Goods;
import me.zhengjie.modules.goods.service.dto.GoodsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoodsMapper extends EntityMapper<GoodsDTO, Goods> {

}
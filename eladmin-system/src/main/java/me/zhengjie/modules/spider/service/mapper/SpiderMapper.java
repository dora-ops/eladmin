package me.zhengjie.modules.spider.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.spider.domain.Spider;
import me.zhengjie.modules.spider.service.dto.SpiderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-19
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpiderMapper extends EntityMapper<SpiderDTO, Spider> {

}
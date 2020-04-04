package me.zhengjie.modules.people.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.people.domain.People;
import me.zhengjie.modules.people.service.dto.PeopleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2020-04-04
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PeopleMapper extends EntityMapper<PeopleDTO, People> {

}
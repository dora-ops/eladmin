package me.zhengjie.modules.person.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.person.domain.Person;
import me.zhengjie.modules.person.service.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author y
* @date 2021-05-15
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

}
package me.zhengjie.modules.userFollow.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.userFollow.domain.UserFollow;
import me.zhengjie.modules.userFollow.service.dto.UserFollowDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author y
* @date 2021-05-15
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserFollowMapper extends EntityMapper<UserFollowDTO, UserFollow> {

}
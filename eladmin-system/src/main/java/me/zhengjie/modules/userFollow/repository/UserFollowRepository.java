package me.zhengjie.modules.userFollow.repository;

import me.zhengjie.modules.userFollow.domain.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author y
* @date 2021-05-15
*/
public interface UserFollowRepository extends JpaRepository<UserFollow, Long>, JpaSpecificationExecutor {
}
package me.zhengjie.modules.handleInfo.repository;

import me.zhengjie.modules.handleInfo.domain.HandleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-04-30
*/
public interface HandleInfoRepository extends JpaRepository<HandleInfo, Long>, JpaSpecificationExecutor {
}
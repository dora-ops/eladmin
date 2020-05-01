package me.zhengjie.modules.closedInfo.repository;

import me.zhengjie.modules.closedInfo.domain.ClosedInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-04-30
*/
public interface ClosedInfoRepository extends JpaRepository<ClosedInfo, Long>, JpaSpecificationExecutor {
}
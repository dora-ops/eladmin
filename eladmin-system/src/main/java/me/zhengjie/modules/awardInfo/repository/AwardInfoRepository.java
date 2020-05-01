package me.zhengjie.modules.awardInfo.repository;

import me.zhengjie.modules.awardInfo.domain.AwardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-04-30
*/
public interface AwardInfoRepository extends JpaRepository<AwardInfo, Long>, JpaSpecificationExecutor {
}
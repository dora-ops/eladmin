package me.zhengjie.modules.investigationInfo.repository;

import me.zhengjie.modules.investigationInfo.domain.InvestigationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-05-02
*/
public interface InvestigationInfoRepository extends JpaRepository<InvestigationInfo, Long>, JpaSpecificationExecutor {
}
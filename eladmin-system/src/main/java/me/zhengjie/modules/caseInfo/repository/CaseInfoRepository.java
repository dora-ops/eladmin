package me.zhengjie.modules.caseInfo.repository;

import me.zhengjie.modules.caseInfo.domain.CaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-05-02
*/
public interface CaseInfoRepository extends JpaRepository<CaseInfo, Long>, JpaSpecificationExecutor {
}
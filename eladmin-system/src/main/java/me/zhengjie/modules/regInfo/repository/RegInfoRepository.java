package me.zhengjie.modules.regInfo.repository;

import me.zhengjie.modules.regInfo.domain.RegInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-05-02
*/
public interface RegInfoRepository extends JpaRepository<RegInfo, Long>, JpaSpecificationExecutor {
}
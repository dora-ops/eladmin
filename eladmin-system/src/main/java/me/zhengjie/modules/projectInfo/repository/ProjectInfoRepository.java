package me.zhengjie.modules.projectInfo.repository;

import me.zhengjie.modules.projectInfo.domain.ProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-04-30
*/
public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Long>, JpaSpecificationExecutor {
}
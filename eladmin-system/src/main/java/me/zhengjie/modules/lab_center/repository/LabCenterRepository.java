package me.zhengjie.modules.lab_center.repository;

import me.zhengjie.modules.lab_center.domain.LabCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-16
*/
public interface LabCenterRepository extends JpaRepository<LabCenter, Long>, JpaSpecificationExecutor {
}
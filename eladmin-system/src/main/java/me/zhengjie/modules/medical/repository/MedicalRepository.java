package me.zhengjie.modules.medical.repository;

import me.zhengjie.modules.medical.domain.Medical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-12
*/
public interface MedicalRepository extends JpaRepository<Medical, Long>, JpaSpecificationExecutor {
}
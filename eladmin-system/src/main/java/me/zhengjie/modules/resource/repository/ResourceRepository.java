package me.zhengjie.modules.resource.repository;

import me.zhengjie.modules.resource.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author y
* @date 2021-05-15
*/
public interface ResourceRepository extends JpaRepository<Resource, Long>, JpaSpecificationExecutor {
}
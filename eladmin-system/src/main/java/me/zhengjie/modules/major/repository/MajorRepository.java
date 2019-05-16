package me.zhengjie.modules.major.repository;

import me.zhengjie.modules.major.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-16
*/
public interface MajorRepository extends JpaRepository<Major, Long>, JpaSpecificationExecutor {
}
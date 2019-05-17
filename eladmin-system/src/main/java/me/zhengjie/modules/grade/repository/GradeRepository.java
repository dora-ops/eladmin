package me.zhengjie.modules.grade.repository;

import me.zhengjie.modules.grade.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-18
*/
public interface GradeRepository extends JpaRepository<Grade, Long>, JpaSpecificationExecutor {
}
package me.zhengjie.modules.course_plan.repository;

import me.zhengjie.modules.course_plan.domain.CoursePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-16
*/
public interface CoursePlanRepository extends JpaRepository<CoursePlan, Long>, JpaSpecificationExecutor {
}
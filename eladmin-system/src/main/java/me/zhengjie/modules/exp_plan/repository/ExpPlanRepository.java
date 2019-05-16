package me.zhengjie.modules.exp_plan.repository;

import me.zhengjie.modules.exp_plan.domain.ExpPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-16
*/
public interface ExpPlanRepository extends JpaRepository<ExpPlan, Long>, JpaSpecificationExecutor {
}
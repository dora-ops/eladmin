package me.zhengjie.modules.experiment.repository;

import me.zhengjie.modules.experiment.domain.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-16
*/
public interface ExperimentRepository extends JpaRepository<Experiment, Long>, JpaSpecificationExecutor {
}
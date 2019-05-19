package me.zhengjie.modules.stu_job.repository;

import me.zhengjie.modules.stu_job.domain.StuJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-19
*/
public interface StuJobRepository extends JpaRepository<StuJob, Long>, JpaSpecificationExecutor {
}
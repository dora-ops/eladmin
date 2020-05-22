package me.zhengjie.modules.exam.repository;

import me.zhengjie.modules.exam.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-05-15
*/
public interface ExamRepository extends JpaRepository<Exam, Long>, JpaSpecificationExecutor {
}
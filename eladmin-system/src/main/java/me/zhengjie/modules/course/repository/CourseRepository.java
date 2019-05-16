package me.zhengjie.modules.course.repository;

import me.zhengjie.modules.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-16
*/
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor {
}
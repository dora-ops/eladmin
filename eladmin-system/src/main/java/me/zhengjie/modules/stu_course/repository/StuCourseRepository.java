package me.zhengjie.modules.stu_course.repository;

import me.zhengjie.modules.stu_course.domain.StuCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-16
*/
public interface StuCourseRepository extends JpaRepository<StuCourse, Long>, JpaSpecificationExecutor {
}
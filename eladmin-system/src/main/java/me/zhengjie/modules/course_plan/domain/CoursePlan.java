package me.zhengjie.modules.course_plan.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Entity
@Data
@Table(name="course_plan")
public class CoursePlan implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 课程名称
     */
    @Column(name = "course_name")
    private String courseName;

    /**
     * 课程号
     */
    @Column(name = "course_number")
    private String courseNumber;

    /**
     * 授课教师
     */
    @Column(name = "tea_name")
    private String teaName;

    /**
     * 教工号
     */
    @Column(name = "tea_number")
    private String teaNumber;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 容纳人数
     */
    @Column(name = "hold")
    private Integer hold;
}
package me.zhengjie.modules.stu_course.domain;

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
@Table(name="stu_course")
public class StuCourse implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "stu_name")
    private String stuName;

    /**
     * 学号
     */
    @Column(name = "stu_number")
    private String stuNumber;

    /**
     * 实验安排计划号
     */
    @Column(name = "explan_number")
    private String explanNumber;

    /**
     * 项目名称
     */
    @Column(name = "exp_name")
    private String expName;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}
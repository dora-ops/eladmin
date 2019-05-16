package me.zhengjie.modules.course.domain;

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
@Table(name="course")
public class Course implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 课程名
     */
    @Column(name = "name")
    private String name;

    /**
     * 课程号
     */
    @Column(name = "number")
    private String number;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 面向专业
     */
    @Column(name = "major")
    private String major;

    /**
     * 总学时
     */
    @Column(name = "period")
    private Integer period;

    /**
     * 理论学时
     */
    @Column(name = "theory")
    private Integer theory;

    /**
     * 实验学时
     */
    @Column(name = "experiment")
    private Integer experiment;

    /**
     * 实验类型
     */
    @Column(name = "type")
    private String type;
}
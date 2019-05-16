package me.zhengjie.modules.experiment.domain;

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
@Table(name="experiment")
public class Experiment implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 项目名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 项目编号
     */
    @Column(name = "number")
    private String number;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 实验内容
     */
    @Column(name = "content")
    private String content;

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

    /**
     * 课程号
     */
    @Column(name = "course_number")
    private String courseNumber;
}
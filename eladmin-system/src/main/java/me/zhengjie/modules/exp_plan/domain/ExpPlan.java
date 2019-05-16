package me.zhengjie.modules.exp_plan.domain;

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
@Table(name="exp_plan")
public class ExpPlan implements Serializable {

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
     * 实验名称
     */
    @Column(name = "exp_name")
    private String expName;

    /**
     * 实验编号
     */
    @Column(name = "exp_number")
    private String expNumber;

    /**
     * 实验房间编号
     */
    @Column(name = "room_number")
    private String roomNumber;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 实验开始时间
     */
    @Column(name = "start_time",nullable = false)
    private Timestamp startTime;

    /**
     * 实验开始时间
     */
    @Column(name = "end_time",nullable = false)
    private Timestamp endTime;

    /**
     * 实验地点
     */
    @Column(name = "exp_address")
    private String expAddress;

    /**
     * 容纳人数
     */
    @Column(name = "hold")
    private Integer hold;

    /**
     * 剩余人数
     */
    @Column(name = "remaind")
    private Integer remaind;
}
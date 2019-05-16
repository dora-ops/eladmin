package me.zhengjie.modules.lab_room.domain;

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
@Table(name="lab_room")
public class LabRoom implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 实验室名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 编号
     */
    @Column(name = "number")
    private String number;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 建筑物名称
     */
    @Column(name = "build")
    private String build;

    /**
     * 所属校区
     */
    @Column(name = "school")
    private String school;

    /**
     * 容纳人数
     */
    @Column(name = "hold")
    private Integer hold;

    /**
     * 所属实验中心编号
     */
    @Column(name = "center_id")
    private Integer centerId;

    /**
     * 实验类别
     */
    @Column(name = "type")
    private String type;

    /**
     * 联系电话
     */
    @Column(name = "tel")
    private String tel;

    /**
     * 主要承担课程
     */
    @Column(name = "charge_course")
    private String chargeCourse;

    /**
     * 面向专业
     */
    @Column(name = "major")
    private String major;
}
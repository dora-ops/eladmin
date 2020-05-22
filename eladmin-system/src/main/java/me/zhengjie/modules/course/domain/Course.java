package me.zhengjie.modules.course.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-19
*/
@Entity
@Data
@Table(name="`course`")
public class Course implements Serializable {

    /**
     * 课程号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "`name`",nullable = false)
    private String name;

    /**
     * 展示图片
     */
    @Column(name = "`img`")
    private String img;

    /**
     * 介绍
     */
    @Column(name = "`introduction`")
    private String introduction;

    /**
     * 创建日期
     */
    @Column(name = "`create_time`",nullable = false)
    private Timestamp createTime;

    /**
     * 老师id
     */
    @Column(name = "`tea_id`")
    private Long teaId;
}
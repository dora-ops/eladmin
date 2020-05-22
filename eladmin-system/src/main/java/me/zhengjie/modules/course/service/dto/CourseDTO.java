package me.zhengjie.modules.course.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-19
*/
@Data
public class CourseDTO implements Serializable {

    /**
     * 课程号
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 展示图片
     */
    private String img;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    /**
     * 老师id
     */
    private Long teaId;
}
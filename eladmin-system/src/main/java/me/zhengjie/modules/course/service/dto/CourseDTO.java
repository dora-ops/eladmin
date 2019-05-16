package me.zhengjie.modules.course.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Data
public class CourseDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 课程名
     */
    private String name;

    /**
     * 课程号
     */
    private String number;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 面向专业
     */
    private String major;

    /**
     * 总学时
     */
    private Integer period;

    /**
     * 理论学时
     */
    private Integer theory;

    /**
     * 实验学时
     */
    private Integer experiment;

    /**
     * 实验类型
     */
    private String type;
}
package me.zhengjie.modules.course_plan.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Data
public class CoursePlanDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程号
     */
    private String courseNumber;

    /**
     * 授课教师
     */
    private String teaName;

    /**
     * 教工号
     */
    private String teaNumber;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 容纳人数
     */
    private Integer hold;
}
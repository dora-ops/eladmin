package me.zhengjie.modules.stu_course.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Data
public class StuCourseDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 学号
     */
    private String stuNumber;

    /**
     * 实验安排计划号
     */
    private String explanNumber;

    /**
     * 项目名称
     */
    private String expName;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
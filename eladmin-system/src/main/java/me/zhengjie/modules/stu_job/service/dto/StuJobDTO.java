package me.zhengjie.modules.stu_job.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-19
*/
@Data
public class StuJobDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 资源id
     */
    private Integer resId;

    /**
     * 资源url
     */
    private String resUrl;

    /**
     * 实验名称
     */
    private String expName;

    /**
     * 实验编号
     */
    private String expNumber;

    /**
     * 授课教师
     */
    private String teaName;

    /**
     * 教工号
     */
    private String teaNumber;

    /**
     * 学生名称
     */
    private String stuName;

    /**
     * 学号
     */
    private String stuNumber;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
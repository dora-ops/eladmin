package me.zhengjie.modules.experiment.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Data
public class ExperimentDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目编号
     */
    private String number;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 实验内容
     */
    private String content;

    /**
     * 实验学时
     */
    private Integer experiment;

    /**
     * 实验类型
     */
    private String type;

    /**
     * 课程号
     */
    private String courseNumber;
}
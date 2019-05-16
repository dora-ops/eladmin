package me.zhengjie.modules.exp_plan.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Data
public class ExpPlanDTO implements Serializable {

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
     * 实验名称
     */
    private String expName;

    /**
     * 实验编号
     */
    private String expNumber;

    /**
     * 实验房间编号
     */
    private String roomNumber;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 实验开始时间
     */
    private Timestamp startTime;

    /**
     * 实验开始时间
     */
    private Timestamp endTime;

    /**
     * 实验地点
     */
    private String expAddress;

    /**
     * 容纳人数
     */
    private Integer hold;

    /**
     * 剩余人数
     */
    private Integer remaind;
}
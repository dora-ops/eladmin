package me.zhengjie.modules.lab_room.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Data
public class LabRoomDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 实验室名称
     */
    private String name;

    /**
     * 编号
     */
    private String number;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 建筑物名称
     */
    private String build;

    /**
     * 所属校区
     */
    private String school;

    /**
     * 容纳人数
     */
    private Integer hold;

    /**
     * 所属实验中心编号
     */
    private Integer centerId;

    /**
     * 实验类别
     */
    private String type;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 主要承担课程
     */
    private String chargeCourse;

    /**
     * 面向专业
     */
    private String major;
}
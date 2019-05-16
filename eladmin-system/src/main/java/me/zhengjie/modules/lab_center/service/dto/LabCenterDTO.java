package me.zhengjie.modules.lab_center.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Data
public class LabCenterDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
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
     * 单位
     */
    private String unit;

    /**
     * 单位id
     */
    private Integer unitId;

    /**
     * 负责人
     */
    private String charge;
}
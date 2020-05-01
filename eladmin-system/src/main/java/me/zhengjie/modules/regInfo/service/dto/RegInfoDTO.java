package me.zhengjie.modules.regInfo.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-30
*/
@Data
public class RegInfoDTO implements Serializable {

    /**
     * 案件编号
     */
    private Long id;

    /**
     * 案件种类
     */
    private String kind;

    /**
     * 案件名称
     */
    private String name;

    /**
     * 重要程度
     */
    private String imp;

    /**
     * 案件简述
     */
    private String summary;

    /**
     * 录入时间
     */
    private Timestamp regTime;

    /**
     * 负责人姓名
     */
    private String uname;

    /**
     * 负责人警号
     */
    private Long uid;

    /**
     * 审核人警号
     */
    private String dealUid;

    /**
     * 提供联系
     */
    private String provider;

    /**
     * 发生时间
     */
    private Timestamp startTime;

    /**
     * 审核人姓名
     */
    private String dealUname;

    /**
     * 案件编号
     */
    private Integer cid;
}
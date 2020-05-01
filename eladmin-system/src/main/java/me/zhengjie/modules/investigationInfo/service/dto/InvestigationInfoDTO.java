package me.zhengjie.modules.investigationInfo.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-30
*/
@Data
public class InvestigationInfoDTO implements Serializable {

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
     * 处理时间
     */
    private Timestamp handleTime;

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
     * 侦查详情
     */
    private String content;

    /**
     * 审核人姓名
     */
    private String dealUname;

    /**
     * 案件编号
     */
    private Integer cid;
}
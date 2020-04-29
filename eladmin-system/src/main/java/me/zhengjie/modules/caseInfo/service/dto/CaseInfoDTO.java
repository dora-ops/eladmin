package me.zhengjie.modules.caseInfo.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-29
*/
@Data
public class CaseInfoDTO implements Serializable {

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
     * 处理结果
     */
    private String result;

    /**
     * 案件简述
     */
    private String summary;

    /**
     * 负责人警号
     */
    private Long uid;

    /**
     * 负责人姓名
     */
    private String uname;

    /**
     * 审核人警号
     */
    private Long deal_id;

    /**
     * 审核人姓名
     */
    private String deal_name;

    /**
     * 案件详情
     */
    private String detail;

    /**
     * 发生时间
     */
    private Timestamp start_time;

    /**
     * 结案时间
     */
    private Timestamp end_time;

    /**
     * 案件状态
     */
    private String status;
}
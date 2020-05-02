package me.zhengjie.modules.caseInfo.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-02
*/
@Entity
@Data
@Table(name="`case_info`")
public class CaseInfo implements Serializable {

    /**
     * 案件编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 案件种类
     */
    @Column(name = "`kind`",nullable = false)
    private String kind;

    /**
     * 案件名称
     */
    @Column(name = "`name`",nullable = false)
    private String name;

    /**
     * 重要程度
     */
    @Column(name = "`imp`",nullable = false)
    private String imp;

    /**
     * 处理结果
     */
    @Column(name = "`result`")
    private String result;

    /**
     * 案件简述
     */
    @Column(name = "`summary`")
    private String summary;

    /**
     * 负责人警号
     */
    @Column(name = "`uid`",nullable = false)
    private Long uid;

    /**
     * 负责人姓名
     */
    @Column(name = "`uname`")
    private String uname;

    /**
     * 审核人警号
     */
    @Column(name = "`deal_id`")
    private Long dealId;

    /**
     * 审核人姓名
     */
    @Column(name = "`deal_name`")
    private String dealName;

    /**
     * 案件详情
     */
    @Column(name = "`detail`")
    private String detail;

    /**
     * 发生时间
     */
    @Column(name = "`start_time`",nullable = false)
    private Timestamp startTime;

    /**
     * 结案时间
     */
    @Column(name = "`end_time`",nullable = false)
    private Timestamp endTime;

    /**
     * 案件状态
     */
    @Column(name = "`status`")
    private String status;
}
package me.zhengjie.modules.investigationInfo.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-30
*/
@Entity
@Data
@Table(name="`investigation_info`")
public class InvestigationInfo implements Serializable {

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
     * 处理时间
     */
    @Column(name = "`handle_time`",nullable = false)
    private Timestamp handleTime;

    /**
     * 负责人姓名
     */
    @Column(name = "`uname`",nullable = false)
    private String uname;

    /**
     * 负责人警号
     */
    @Column(name = "`uid`",nullable = false)
    private Long uid;

    /**
     * 审核人警号
     */
    @Column(name = "`deal_uid`")
    private String dealUid;

    /**
     * 侦查详情
     */
    @Column(name = "`content`",nullable = false)
    private String content;

    /**
     * 审核人姓名
     */
    @Column(name = "`deal_uname`")
    private String dealUname;

    /**
     * 案件编号
     */
    @Column(name = "`cid`",nullable = false)
    private Integer cid;
}
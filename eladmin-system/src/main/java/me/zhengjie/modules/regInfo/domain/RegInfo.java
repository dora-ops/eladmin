package me.zhengjie.modules.regInfo.domain;

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
@Table(name="`reg_info`")
public class RegInfo implements Serializable {

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
    @Column(name = "`imp`")
    private String imp;

    /**
     * 案件简述
     */
    @Column(name = "`summary`")
    private String summary;

    /**
     * 录入时间
     */
    @Column(name = "`reg_time`",nullable = false)
    private Timestamp regTime;

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
     * 提供联系
     */
    @Column(name = "`provider`")
    private String provider;

    /**
     * 发生时间
     */
    @Column(name = "`start_time`",nullable = false)
    private Timestamp startTime;

    /**
     * 审核人姓名
     */
    @Column(name = "`deal_uname`")
    private String dealUname;

    /**
     * 案件编号
     */
    @Column(name = "`cid`")
    private Integer cid;
}
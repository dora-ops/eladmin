package me.zhengjie.modules.closedInfo.domain;

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
@Table(name="`closed_info`")
public class ClosedInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 案件编号
     */
    @Column(name = "`cid`",nullable = false)
    private Long cid;

    /**
     * 案件名称
     */
    @Column(name = "`name`",nullable = false)
    private String name;

    /**
     * 结案时间
     */
    @Column(name = "`end_time`",nullable = false)
    private Timestamp endTime;

    /**
     * 奖惩人姓名
     */
    @Column(name = "`uname`",nullable = false)
    private String uname;

    /**
     * 奖惩人警号
     */
    @Column(name = "`uid`",nullable = false)
    private Long uid;

    /**
     * 处理人警号
     */
    @Column(name = "`deal_uid`")
    private String dealUid;

    /**
     * 案件汇报
     */
    @Column(name = "`content`",nullable = false)
    private String content;

    /**
     * 审核人姓名
     */
    @Column(name = "`deal_name`")
    private String dealName;
}
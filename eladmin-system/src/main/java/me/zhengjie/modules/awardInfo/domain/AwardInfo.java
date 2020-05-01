package me.zhengjie.modules.awardInfo.domain;

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
@Table(name="`award_info`")
public class AwardInfo implements Serializable {

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
     * 处理时间
     */
    @Column(name = "`handle_time`",nullable = false)
    private Timestamp handleTime;

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
     * 奖惩详情
     */
    @Column(name = "`content`",nullable = false)
    private String content;

    /**
     * 处理人姓名
     */
    @Column(name = "`handle_uname`")
    private String handleUname;
}
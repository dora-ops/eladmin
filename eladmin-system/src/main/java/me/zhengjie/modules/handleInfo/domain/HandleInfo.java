package me.zhengjie.modules.handleInfo.domain;

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
@Table(name="`handle_info`")
public class HandleInfo implements Serializable {

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
     * 发送时间
     */
    @Column(name = "`send_time`",nullable = false)
    private Timestamp sendTime;

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
    @Column(name = "`deal_uid`",nullable = false)
    private String dealUid;

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
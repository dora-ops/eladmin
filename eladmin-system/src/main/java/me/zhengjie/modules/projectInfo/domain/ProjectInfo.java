package me.zhengjie.modules.projectInfo.domain;

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
@Table(name="`project_info`")
public class ProjectInfo implements Serializable {

    /**
     * 案件编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 案件名称
     */
    @Column(name = "`name`",nullable = false)
    private String name;

    /**
     * 提出时间
     */
    @Column(name = "`raise_time`",nullable = false)
    private Timestamp raiseTime;

    /**
     * 执行时间
     */
    @Column(name = "`execute_time`",nullable = false)
    private Timestamp executeTime;

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
package me.zhengjie.modules.handleNotice.domain;

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
@Table(name="`handle_notice`")
public class HandleNotice implements Serializable {

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
     * 处理结果
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 审核姓名
     */
    @Column(name = "`handle_uname`")
    private String handleUname;
}
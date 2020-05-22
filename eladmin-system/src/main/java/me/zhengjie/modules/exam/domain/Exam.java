package me.zhengjie.modules.exam.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-15
*/
@Entity
@Data
@Table(name="`exam`")
public class Exam implements Serializable {

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 试卷名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 试卷类型
     */
    @Column(name = "`type`")
    private String type;

    /**
     * 作答时间
     */
    @Column(name = "`time`",nullable = false)
    private Timestamp time;

    /**
     * 合格分
     */
    @Column(name = "`ok_score`",nullable = false)
    private String okScore;

    /**
     * 出卷人编号
     */
    @Column(name = "`create_id`",nullable = false)
    private String createId;

    /**
     * 出卷人名称
     */
    @Column(name = "`create_name`",nullable = false)
    private String createName;

    /**
     * 考试开始时间
     */
    @Column(name = "`start_time`",nullable = false)
    private Timestamp startTime;

    /**
     * 考试结束时间
     */
    @Column(name = "`end_time`",nullable = false)
    private Timestamp endTime;
}
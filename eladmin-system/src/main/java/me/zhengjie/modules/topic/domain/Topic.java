package me.zhengjie.modules.topic.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-21
*/
@Entity
@Data
@Table(name="`topic`")
public class Topic implements Serializable {

    /**
     * 题号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 习题题目
     */
    @Column(name = "`description`",nullable = false)
    private String description;

    /**
     * 习题类型
     */
    @Column(name = "`type`",nullable = false)
    private String type;

    /**
     * 答案a
     */
    @Column(name = "`ans_a`")
    private String ansA;

    /**
     * 答案b
     */
    @Column(name = "`ans_b`")
    private String ansB;

    /**
     * 答案c
     */
    @Column(name = "`ans_c`")
    private String ansC;

    /**
     * 答案d
     */
    @Column(name = "`ans_d`")
    private String ansD;

    /**
     * 正确答案
     */
    @Column(name = "`ans`",nullable = false)
    private String ans;

    /**
     * 试卷id
     */
    @Column(name = "`exam_id`",nullable = false)
    private String examId;
}
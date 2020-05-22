package me.zhengjie.modules.grade.domain;

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
@Table(name="`grade`")
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 试卷名
     */
    @Column(name = "`exam_name`",nullable = false)
    private String examName;

    /**
     * 试卷id
     */
    @Column(name = "`exam_id`",nullable = false)
    private Integer examId;

    /**
     * 学号
     */
    @Column(name = "`user_id`",nullable = false)
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "`user_name`",nullable = false)
    private String userName;

    /**
     * 成绩
     */
    @Column(name = "`score`",nullable = false)
    private String score;

    /**
     * 答对率
     */
    @Column(name = "`average`",nullable = false)
    private String average;

    /**
     * 创建日期
     */
    @Column(name = "`create_time`",nullable = false)
    private Timestamp createTime;
}
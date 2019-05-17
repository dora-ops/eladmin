package me.zhengjie.modules.grade.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-18
*/
@Entity
@Data
@Table(name="grade")
public class Grade implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 分数
     */
    @Column(name = "score",nullable = false)
    private Integer score;

    /**
     * 老师
     */
    @Column(name = "tea")
    private String tea;

    /**
     * 学生
     */
    @Column(name = "cus")
    private String cus;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}
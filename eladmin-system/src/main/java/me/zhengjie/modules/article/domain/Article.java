package me.zhengjie.modules.article.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-17
*/
@Entity
@Data
@Table(name="article")
public class Article implements Serializable {

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
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 创建者
     */
    @Column(name = "cus")
    private String cus;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 教师
     */
    @Column(name = "tea")
    private String tea;

    @Column(name = "state")
    private String state;

    @Column(name = "read")
    private Integer read;
}
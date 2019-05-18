package me.zhengjie.modules.article.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-19
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
     * 摘要
     */
    @Column(name = "con_tent")
    private String conTent;

    /**
     * 阅读
     */
    @Column(name = "re_ad")
    private String reAd;

    /**
     * 评论
     */
    @Column(name = "com_ment")
    private String comMent;

    /**
     * 时间
     */
    @Column(name = "pub_time")
    private Timestamp pubTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}
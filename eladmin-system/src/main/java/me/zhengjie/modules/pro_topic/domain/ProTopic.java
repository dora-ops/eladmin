package me.zhengjie.modules.pro_topic.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-12
*/
@Entity
@Data
@Table(name="pro_topic")
public class ProTopic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 标题
     */
    @Column(name = "title",nullable = false)
    private String title;

    /**
     * 是否置顶
     */
    @Column(name = "is_top")
    private Integer isTop;

    /**
     * 阅读次数
     */
    @Column(name = "count")
    private Integer count;

    /**
     * 内容
     */
    @Column(name = "content",nullable = false)
    private String content;

    /**
     * 创建日期
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}
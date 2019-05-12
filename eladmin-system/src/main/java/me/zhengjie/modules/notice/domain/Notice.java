package me.zhengjie.modules.notice.domain;

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
@Table(name="notice")
public class Notice implements Serializable {

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
     * 类型
     */
    @Column(name = "type",nullable = false)
    private String type;

    /**
     * 发送人id
     */
    @Column(name = "send")
    private Integer send;

    /**
     * 接受人id
     */
    @Column(name = "receive")
    private Integer receive;

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
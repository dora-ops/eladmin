package me.zhengjie.modules.notice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-18
*/
@Entity
@Data
@Table(name="notice")
@AllArgsConstructor
@NoArgsConstructor
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
     * 发送人
     */
    @Column(name = "send")
    private String send;

    /**
     * 接受人
     */
    @Column(name = "receive")
    private String receive;

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
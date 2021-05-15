package me.zhengjie.modules.commentlist.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author y
* @date 2021-05-15
*/
@Entity
@Data
@Table(name="`commentlist`")
public class Commentlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "`cus_nickname`",nullable = false)
    private String cusNickname;

    /**
     * 评论内容
     */
    @Column(name = "`comment`")
    private String comment;

    /**
     * 评论用户id
     */
    @Column(name = "`cus_id`",nullable = false)
    private Integer cusId;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`",nullable = false)
    private Timestamp createTime;

    /**
     * 发布id
     */
    @Column(name = "`pub_id`",nullable = false)
    private Integer pubId;
}
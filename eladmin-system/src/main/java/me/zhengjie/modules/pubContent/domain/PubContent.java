package me.zhengjie.modules.pubContent.domain;

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
@Table(name="`pub_content`")
public class PubContent implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 标题
     */
    @Column(name = "`title`")
    private String title;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`",nullable = false)
    private Timestamp createTime;

    /**
     * 介绍
     */
    @Column(name = "`description`")
    private String description;

    /**
     * 属性
     */
    @Column(name = "`property`")
    private String property;

    /**
     * 标签
     */
    @Column(name = "`tag`")
    private String tag;

    /**
     * 资源id
     */
    @Column(name = "`res_id`")
    private String resId;

    /**
     * 用户id
     */
    @Column(name = "`cus_id`")
    private String cusId;

    /**
     * 是否打水印
     */
    @Column(name = "`flag`")
    private Integer flag;

    /**
     * 地址
     */
    @Column(name = "`url`")
    private String url;

    /**
     * 喜欢
     */
    @Column(name = "`like_count`",nullable = false)
    private Integer likeCount;

    /**
     * 收藏
     */
    @Column(name = "`com_count`",nullable = false)
    private Integer comCount;
}
package me.zhengjie.modules.pubContent.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author y
* @date 2021-05-15
*/
@Data
public class PubContentDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 介绍
     */
    private String description;

    /**
     * 属性
     */
    private String property;

    /**
     * 标签
     */
    private String tag;

    /**
     * 资源id
     */
    private String resId;

    /**
     * 用户id
     */
    private String cusId;

    /**
     * 是否打水印
     */
    private Integer flag;

    /**
     * 地址
     */
    private String url;

    /**
     * 喜欢
     */
    private Integer likeCount;

    /**
     * 收藏
     */
    private Integer comCount;
}
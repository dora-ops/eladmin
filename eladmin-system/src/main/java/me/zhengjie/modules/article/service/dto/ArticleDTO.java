package me.zhengjie.modules.article.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-19
*/
@Data
public class ArticleDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String conTent;

    /**
     * 阅读
     */
    private String reAd;

    /**
     * 评论
     */
    private String comMent;

    /**
     * 时间
     */
    private Timestamp pubTime;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
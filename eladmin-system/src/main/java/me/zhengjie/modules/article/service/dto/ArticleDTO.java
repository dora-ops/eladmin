package me.zhengjie.modules.article.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-17
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
     * 内容
     */
    private String content;

    /**
     * 创建者
     */
    private String cus;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 教师
     */
    private String tea;

    private Integer read;
}
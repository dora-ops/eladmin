package me.zhengjie.modules.commentlist.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author y
* @date 2021-05-15
*/
@Data
public class CommentlistDTO implements Serializable {

    private Long id;

    /**
     * 用户名
     */
    private String cusNickname;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论用户id
     */
    private Integer cusId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 发布id
     */
    private Integer pubId;
}
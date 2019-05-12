package me.zhengjie.modules.topic.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-12
*/
@Data
public class TopicDTO implements Serializable {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 阅读次数
     */
    private Integer count;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}
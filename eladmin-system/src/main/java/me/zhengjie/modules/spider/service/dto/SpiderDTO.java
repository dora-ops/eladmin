package me.zhengjie.modules.spider.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-19
*/
@Data
public class SpiderDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 爬虫code
     */
    private String code;

    /**
     * 域名
     */
    private String domain;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
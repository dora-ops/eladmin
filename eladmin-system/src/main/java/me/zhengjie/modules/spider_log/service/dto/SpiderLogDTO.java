package me.zhengjie.modules.spider_log.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-19
*/
@Data
public class SpiderLogDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 数据
     */
    private String data;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求头
     */
    private String header;
}
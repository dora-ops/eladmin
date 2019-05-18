package me.zhengjie.modules.spider_task.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-18
*/
@Data
public class SpiderTaskDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 状态
     */
    private String state;

    /**
     * 策略id
     */
    private Integer infoId;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
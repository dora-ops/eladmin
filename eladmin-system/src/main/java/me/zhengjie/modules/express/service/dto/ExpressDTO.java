package me.zhengjie.modules.express.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-12
*/
@Data
public class ExpressDTO implements Serializable {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 发货人id
     */
    private Integer send;

    /**
     * 收货人id
     */
    private Integer receive;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}
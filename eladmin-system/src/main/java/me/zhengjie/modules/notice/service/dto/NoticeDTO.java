package me.zhengjie.modules.notice.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-12
*/
@Data
public class NoticeDTO implements Serializable {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型
     */
    private String type;

    /**
     * 发送人id
     */
    private Integer send;

    /**
     * 接受人id
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
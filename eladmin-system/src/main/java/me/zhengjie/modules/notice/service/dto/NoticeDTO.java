package me.zhengjie.modules.notice.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-18
*/
@Data
public class NoticeDTO implements Serializable {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 发送人
     */
    private String send;

    /**
     * 接受人
     */
    private String receive;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}
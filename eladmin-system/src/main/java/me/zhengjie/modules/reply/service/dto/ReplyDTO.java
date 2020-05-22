package me.zhengjie.modules.reply.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-21
*/
@Data
public class ReplyDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 咨询内容
     */
    private String text;

    /**
     * 回复内容
     */
    private String reply;

    /**
     * 学号
     */
    private String stuid;

    /**
     * 名字
     */
    private String stuname;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    /**
     * 老师id
     */
    private Long teaid;
}
package me.zhengjie.modules.handleNotice.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-30
*/
@Data
public class HandleNoticeDTO implements Serializable {

    private Long id;

    /**
     * 案件编号
     */
    private Long cid;

    /**
     * 案件名称
     */
    private String name;

    /**
     * 处理时间
     */
    private Timestamp handleTime;

    /**
     * 负责人姓名
     */
    private String uname;

    /**
     * 负责人警号
     */
    private Long uid;

    /**
     * 处理结果
     */
    private String content;

    /**
     * 审核姓名
     */
    private String handleUname;
}
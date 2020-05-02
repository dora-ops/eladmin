package me.zhengjie.modules.closedInfo.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-02
*/
@Data
public class ClosedInfoDTO implements Serializable {

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
     * 结案时间
     */
    private Timestamp endTime;

    /**
     * 奖惩人姓名
     */
    private String uname;

    /**
     * 奖惩人警号
     */
    private Long uid;

    /**
     * 处理人警号
     */
    private String dealUid;

    /**
     * 案件汇报
     */
    private String content;

    /**
     * 审核人姓名
     */
    private String dealName;
}
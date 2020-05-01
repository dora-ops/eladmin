package me.zhengjie.modules.awardInfo.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-30
*/
@Data
public class AwardInfoDTO implements Serializable {

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
     * 奖惩详情
     */
    private String content;

    /**
     * 处理人姓名
     */
    private String handleUname;
}
package me.zhengjie.modules.handleInfo.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-02
*/
@Data
public class HandleInfoDTO implements Serializable {

    private Long id;

    /**
     * 案件种类
     */
    private String kind;

    /**
     * 案件名称
     */
    private String name;

    /**
     * 重要程度
     */
    private String imp;

    /**
     * 处理结果
     */
    private String result;

    /**
     * 发送时间
     */
    private Timestamp sendTime;

    /**
     * 负责人姓名
     */
    private String uname;

    /**
     * 负责人警号
     */
    private Long uid;

    /**
     * 审核人警号
     */
    private String dealUid;

    /**
     * 审核人姓名
     */
    private String dealUname;

    /**
     * 案件编号
     */
    private Integer cid;
}
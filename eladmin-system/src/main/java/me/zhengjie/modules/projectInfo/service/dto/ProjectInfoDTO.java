package me.zhengjie.modules.projectInfo.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-30
*/
@Data
public class ProjectInfoDTO implements Serializable {

    /**
     * 案件编号
     */
    private Long id;

    /**
     * 案件名称
     */
    private String name;

    /**
     * 提出时间
     */
    private Timestamp raiseTime;

    /**
     * 执行时间
     */
    private Timestamp executeTime;

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
     * 侦查详情
     */
    private String content;

    /**
     * 审核人姓名
     */
    private String dealUname;

    /**
     * 案件编号
     */
    private Integer cid;
}
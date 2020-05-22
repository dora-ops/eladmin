package me.zhengjie.modules.tea_resource.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-15
*/
@Data
public class TeaResourceDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 教案名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 教案类型
     */
    private Integer type;

    /**
     * 文件后缀
     */
    private String fileType;

    /**
     * 是否是特色教案
     */
    private Integer isSpecial;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 简介
     */
    private String info;

    /**
     * 上传人编号
     */
    private String createId;

    /**
     * 上传人姓名
     */
    private String createName;

    /**
     * 班级id
     */
    private Integer claId;
}
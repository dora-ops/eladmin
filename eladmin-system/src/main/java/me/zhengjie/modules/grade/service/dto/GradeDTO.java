package me.zhengjie.modules.grade.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-18
*/
@Data
public class GradeDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 老师
     */
    private String tea;

    /**
     * 学生
     */
    private String cus;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
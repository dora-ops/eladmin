package me.zhengjie.modules.grade.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-15
*/
@Data
public class GradeDTO implements Serializable {

    private Long id;

    /**
     * 试卷名
     */
    private String examName;

    /**
     * 试卷id
     */
    private Integer examId;

    /**
     * 学号
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 成绩
     */
    private String score;

    /**
     * 答对率
     */
    private String average;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}
package me.zhengjie.modules.exam.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-15
*/
@Data
public class ExamDTO implements Serializable {

    /**
     * 编号
     */
    private Long id;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 试卷类型
     */
    private String type;

    /**
     * 作答时间
     */
    private Timestamp time;

    /**
     * 合格分
     */
    private String okScore;

    /**
     * 出卷人编号
     */
    private String createId;

    /**
     * 出卷人名称
     */
    private String createName;

    /**
     * 考试开始时间
     */
    private Timestamp startTime;

    /**
     * 考试结束时间
     */
    private Timestamp endTime;
}
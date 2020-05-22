package me.zhengjie.modules.topic.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-21
*/
@Data
public class TopicDTO implements Serializable {

    /**
     * 题号
     */
    private Long id;

    /**
     * 习题题目
     */
    private String description;

    /**
     * 习题类型
     */
    private String type;

    /**
     * 答案a
     */
    private String ansA;

    /**
     * 答案b
     */
    private String ansB;

    /**
     * 答案c
     */
    private String ansC;

    /**
     * 答案d
     */
    private String ansD;

    /**
     * 正确答案
     */
    private String ans;

    /**
     * 试卷id
     */
    private String examId;
}
package me.zhengjie.modules.suggest.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-21
*/
@Data
public class SuggestDTO implements Serializable {

    /**
     * 编号
     */
    private Long id;

    /**
     * 咨询内容
     */
    private String text;

    /**
     * 学号
     */
    private String stuid;

    /**
     * 名字
     */
    private String stuname;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    /**
     * 老师id
     */
    private Long teaid;
}
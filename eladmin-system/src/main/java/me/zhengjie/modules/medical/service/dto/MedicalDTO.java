package me.zhengjie.modules.medical.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-12
*/
@Data
public class MedicalDTO implements Serializable {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 医疗记录
     */
    private String record;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}
package me.zhengjie.modules.major.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Data
public class MajorDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编号
     */
    private String number;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 学院
     */
    private String yard;
}
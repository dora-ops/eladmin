package me.zhengjie.modules.tea_resource.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-15
*/
@Entity
@Data
@Table(name="`tea_resource`")
public class TeaResource implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 教案名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`",nullable = false)
    private Timestamp createTime;

    /**
     * 教案类型
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 文件后缀
     */
    @Column(name = "`file_type`")
    private String fileType;

    /**
     * 是否是特色教案
     */
    @Column(name = "`is_special`")
    private Integer isSpecial;

    /**
     * 评分
     */
    @Column(name = "`score`")
    private Integer score;

    /**
     * 简介
     */
    @Column(name = "`info`")
    private String info;

    /**
     * 上传人编号
     */
    @Column(name = "`create_id`")
    private String createId;

    /**
     * 上传人姓名
     */
    @Column(name = "`create_name`")
    private String createName;

    /**
     * 班级id
     */
    @Column(name = "`cla_id`")
    private Integer claId;
}
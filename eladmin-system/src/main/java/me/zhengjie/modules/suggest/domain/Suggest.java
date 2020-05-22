package me.zhengjie.modules.suggest.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-05-21
*/
@Entity
@Data
@Table(name="`suggest`")
public class Suggest implements Serializable {

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 咨询内容
     */
    @Column(name = "`text`")
    private String text;

    /**
     * 学号
     */
    @Column(name = "`stuid`")
    private String stuid;

    /**
     * 名字
     */
    @Column(name = "`stuname`")
    private String stuname;

    /**
     * 创建日期
     */
    @Column(name = "`create_time`",nullable = false)
    private Timestamp createTime;

    /**
     * 老师id
     */
    @Column(name = "`teaid`")
    private Long teaid;
}
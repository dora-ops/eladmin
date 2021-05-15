package me.zhengjie.modules.resource.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author y
* @date 2021-05-15
*/
@Entity
@Data
@Table(name="`resource`")
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 路径
     */
    @Column(name = "`filepath`")
    private String filepath;

    /**
     * 生成文件名
     */
    @Column(name = "`filename`")
    private String filename;

    /**
     * 原文件名称
     */
    @Column(name = "`originalname`")
    private String originalname;
}
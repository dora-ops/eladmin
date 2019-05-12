package me.zhengjie.modules.erea_info.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-12
*/
@Entity
@Data
@Table(name="erea_info")
public class EreaInfo implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 服务电话
     */
    @Column(name = "tel")
    private String tel;

    /**
     * 首页展示的img
     */
    @Column(name = "img")
    private String img;

    /**
     * 地址
     */
    @Column(name = "adress")
    private String adress;
}
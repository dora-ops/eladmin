package me.zhengjie.modules.car.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-04
*/
@Entity
@Data
@Table(name="car")
public class Car implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 车名
     */
    @Column(name = "`name`",nullable = false)
    private String name;

    /**
     * 价格
     */
    @Column(name = "price",nullable = false)
    private String price;

    /**
     * 牌子
     */
    @Column(name = "`type`",nullable = false)
    private String type;

    /**
     * 状态
     */
    @Column(name = "`status`",nullable = false)
    private String status;

    /**
     * 介绍
     */
    @Column(name = "introduction",nullable = false)
    private String introduction;

    /**
     * 描述
     */
    @Column(name = "`desc`",nullable = false)
    private String desc;

    /**
     * 图片
     */
    @Column(name = "img",nullable = false)
    private String img;

    /**
     * 发布人
     */
    @Column(name = "uid",nullable = false)
    private Long uid;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    //会有注解异常，org.hibernate.AnnotationException: @Temporal should only be set on a java.util.Date or java.util.Calendar property: me.zhengjie.modules.car.domain.Car.createTime
    private Timestamp createTime;
}
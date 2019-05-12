package me.zhengjie.modules.goods.domain;

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
@Table(name="goods")
public class Goods implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name",nullable = false)
    private String name;

    /**
     * 描述
     */
    @Column(name = "desciption",nullable = false)
    private String desciption;

    /**
     * 价格
     */
    @Column(name = "price")
    private Integer price;

    /**
     * 创建日期
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}
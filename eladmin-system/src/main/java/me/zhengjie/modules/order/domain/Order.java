package me.zhengjie.modules.order.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-04
*/
@Entity
@Data
@Table(name="`order`")
public class Order implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 花费
     */
    @Column(name = "price",nullable = false)
    private String price;

    /**
     * 行为
     */
    @Column(name = "type",nullable = false)
    private String type;

    /**
     * 用户
     */
    @Column(name = "uid",nullable = false)
    private String uid;
}
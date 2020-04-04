package me.zhengjie.modules.car.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-04
*/
@Data
public class CarDTO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 车名
     */
    private String name;

    /**
     * 价格
     */
    private String price;

    /**
     * 牌子
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * 描述
     */
    private String desc;

    /**
     * 图片
     */
    private String img;

    /**
     * 发布人
     */
    private Long uid;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
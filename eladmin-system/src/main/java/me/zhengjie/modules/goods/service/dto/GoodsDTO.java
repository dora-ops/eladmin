package me.zhengjie.modules.goods.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-12
*/
@Data
public class GoodsDTO implements Serializable {

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String desciption;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}
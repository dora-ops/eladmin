package me.zhengjie.modules.order.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-04
*/
@Data
public class OrderDTO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 花费
     */
    private String price;

    /**
     * 行为
     */
    private String type;

    /**
     * 用户
     */
    private String uid;
}
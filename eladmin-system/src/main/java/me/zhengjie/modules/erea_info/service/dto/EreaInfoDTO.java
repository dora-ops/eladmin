package me.zhengjie.modules.erea_info.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-12
*/
@Data
public class EreaInfoDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 服务电话
     */
    private String tel;

    /**
     * 首页展示的img
     */
    private String img;

    /**
     * 地址
     */
    private String adress;
}
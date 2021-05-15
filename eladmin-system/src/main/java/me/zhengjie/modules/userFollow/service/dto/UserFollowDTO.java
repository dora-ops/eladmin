package me.zhengjie.modules.userFollow.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author y
* @date 2021-05-15
*/
@Data
public class UserFollowDTO implements Serializable {

    private Long id;

    /**
     * 被关注用户id
     */
    private Integer cusId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 关注用户id
     */
    private Integer cusFollowId;
}
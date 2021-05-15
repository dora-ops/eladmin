package me.zhengjie.modules.userFollow.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author y
* @date 2021-05-15
*/
@Entity
@Data
@Table(name="`user_follow`")
public class UserFollow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 被关注用户id
     */
    @Column(name = "`cus_id`",nullable = false)
    private Integer cusId;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`",nullable = false)
    private Timestamp createTime;

    /**
     * 关注用户id
     */
    @Column(name = "`cus_follow_id`",nullable = false)
    private Integer cusFollowId;
}
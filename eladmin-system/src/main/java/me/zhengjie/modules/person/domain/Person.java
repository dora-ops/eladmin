package me.zhengjie.modules.person.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author y
* @date 2021-05-15
*/
@Entity
@Data
@Table(name="`person`")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 签名
     */
    @Column(name = "`bio`")
    private String bio;

    /**
     * 关注者
     */
    @Column(name = "`followers`")
    private String followers;

    /**
     * 手机号
     */
    @Column(name = "`mobile`",unique = true,nullable = false)
    private String mobile;

    /**
     * 用户名
     */
    @Column(name = "`nickname`",nullable = false)
    private String nickname;

    /**
     * 密码
     */
    @Column(name = "`password`",nullable = false)
    private String password;

    /**
     * 头像
     */
    @Column(name = "`photo`")
    private String photo;
}
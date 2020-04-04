package me.zhengjie.modules.people.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-04
*/
@Entity
@Data
@Table(name="people")
public class People implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 角色
     */
    @Column(name = "role",nullable = false)
    private String role;

    /**
     * 用户名
     */
    @Column(name = "username",nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password",nullable = false)
    private String password;
}
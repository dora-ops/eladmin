package me.zhengjie.modules.people.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2020-04-04
*/
@Data
public class PeopleDTO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 角色
     */
    private String role;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
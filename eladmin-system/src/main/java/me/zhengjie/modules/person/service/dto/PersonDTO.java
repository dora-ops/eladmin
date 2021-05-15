package me.zhengjie.modules.person.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author y
* @date 2021-05-15
*/
@Data
public class PersonDTO implements Serializable {

    private Long id;

    /**
     * 签名
     */
    private String bio;

    /**
     * 关注者
     */
    private String followers;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户名
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String photo;
}
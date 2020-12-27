package com.floating.config.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr_Fei
 * @description 用户信息对象
 * @date 2020-12-21 23:40
 */
@Data
public class AccountProfile implements Serializable {

    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户手机号
     */
    private String telephone;
}

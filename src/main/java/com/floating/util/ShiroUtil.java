package com.floating.util;

import com.floating.config.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author Mr_Fei
 * @description shiro工具类
 * @date 2020-12-24 2:12
 */
public class ShiroUtil {

    /**
     * 获取用户登录信息
     *
     * @return com.floating.config.shiro.AccountProfile
     * @author Mr_Fei
     * @date 2020/12/24 2:12
     * @description getProfile
     */
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}

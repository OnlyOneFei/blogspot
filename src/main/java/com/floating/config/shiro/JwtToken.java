package com.floating.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Mr_Fei
 * @description
 * @date 2020-12-21 23:39
 */
public class JwtToken implements AuthenticationToken {
    private String token;
    public JwtToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}

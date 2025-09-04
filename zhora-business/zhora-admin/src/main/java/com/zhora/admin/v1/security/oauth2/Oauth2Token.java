package com.zhora.admin.v1.security.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author zhehen.lu
 */
public class Oauth2Token implements AuthenticationToken {
    private String token;

    public Oauth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

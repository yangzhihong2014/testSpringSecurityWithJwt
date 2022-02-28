package com.example.testSpringSecurityWithJwt.jwt;

import org.springframework.security.core.GrantedAuthority;


/**
 * 实现 GrantedAuthority 同时自定义 权限字段
 *      GrantedAuthority 默认实现的三个子类都是只有角色字段的
 */
public class GrantedAuthorityProvider implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityProvider(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}

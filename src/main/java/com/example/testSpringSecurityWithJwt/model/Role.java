package com.example.testSpringSecurityWithJwt.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/25
 */
@TableName(value = "tb_role")
public class Role {
    private String id;
    private String role;
    private List<Authority> authoritys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Authority> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(List<Authority> authoritys) {
        this.authoritys = authoritys;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", authoritys=" + authoritys +
                '}';
    }
}
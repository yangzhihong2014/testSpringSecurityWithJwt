package com.example.testSpringSecurityWithJwt.model;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/25
 */
@TableName(value = "tb_user_role")
public class UserRole {
    private String id;
    private String tbUserId;
    private String tbRoleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTbUserId() {
        return tbUserId;
    }

    public void setTbUserId(String tbUserId) {
        this.tbUserId = tbUserId;
    }

    public String getTbRoleId() {
        return tbRoleId;
    }

    public void setTbRoleId(String tbRoleId) {
        this.tbRoleId = tbRoleId;
    }
}
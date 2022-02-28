package com.example.testSpringSecurityWithJwt.model;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/25
 */
@TableName(value = "tb_role_authority")
public class RoleAuthority {
    private String id;
    private String tbRoleId;
    private String tbAuthorityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTbRoleId() {
        return tbRoleId;
    }

    public void setTbRoleId(String tbRoleId) {
        this.tbRoleId = tbRoleId;
    }

    public String getTbAuthority() {
        return tbAuthorityId;
    }

    public void setTbAuthority(String tbAuthority) {
        this.tbAuthorityId = tbAuthority;
    }

    @Override
    public String toString() {
        return "RoleAuthority{" +
                "id='" + id + '\'' +
                ", tbRoleId='" + tbRoleId + '\'' +
                ", tbAuthorityId='" + tbAuthorityId + '\'' +
                '}';
    }
}
package com.example.testSpringSecurityWithJwt.mapper;

import com.example.testSpringSecurityWithJwt.model.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/25
 */
@Mapper
public interface AuthorityMapper {

    @Select(" select * from tb_authority where id in (" +
            " select r.tb_authority_id from tb_role_authority r where r.tb_role_id =#{roleId} ) ")
    List<Authority> listByRoleId(@Param("roleId") String roleId);
}
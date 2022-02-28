package com.example.testSpringSecurityWithJwt.mapper;

import com.example.testSpringSecurityWithJwt.model.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/25
 */
@Mapper
public interface RoleMapper {

    @Select(" SELECT * FROM tb_role r where r.id in ( " +
            "   SELECT u.tb_role_id from tb_user_role u where u.tb_user_id = #{userId} " +
            "   )")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(column = "id",property = "authoritys",javaType= List.class,
                    //many指示我们，查询出来的结果有很多个
                    many = @Many(
                            //select = sql语句
                            select = "com.example.testSpringSecurityWithJwt.mapper.AuthorityMapper.listByRoleId",
                            //懒加载
                            fetchType = FetchType.LAZY))
    })
    List<Role> listByUserId(@Param("userId") String userId);
}

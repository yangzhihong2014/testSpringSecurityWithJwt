package com.example.testSpringSecurityWithJwt.mapper;

import com.example.testSpringSecurityWithJwt.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/25
 */
@Mapper
public interface UserMapper {
    @Select(" select * from tb_user where username = #{username}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(column = "id",property = "roles",javaType= List.class,
                    //many指示我们，查询出来的结果有很多个
                    many = @Many(
                            //select = sql语句
                            select = "com.example.testSpringSecurityWithJwt.mapper.RoleMapper.listByUserId",
                            //懒加载
                            fetchType = FetchType.LAZY))
    })
    User findByUsername(@Param("username") String username);


    @Select(" select * from tb_user ")
    List<User> list();
}
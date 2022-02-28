package com.example.testSpringSecurityWithJwt;

import com.example.testSpringSecurityWithJwt.mapper.UserMapper;
import com.example.testSpringSecurityWithJwt.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestSpringSecurityWithJwtApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private UserMapper userMapper;

    @Test
    void findByUsername() {
        User yang = userMapper.findByUsername("yang");
        System.out.println(yang);
    }

}

package com.example.testSpringSecurityWithJwt.jwt;

import com.example.testSpringSecurityWithJwt.mapper.UserMapper;
import com.example.testSpringSecurityWithJwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 固定实现的方法，service
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {

            // 返回是根据权限创建的，还是角色创建的控制对象
            return JwtUserFactory.create(user);
        }
    }
}
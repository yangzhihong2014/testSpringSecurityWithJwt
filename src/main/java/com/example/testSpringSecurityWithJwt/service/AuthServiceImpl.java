package com.example.testSpringSecurityWithJwt.service;

import com.example.testSpringSecurityWithJwt.jwt.JwtTokenUtils;
import com.example.testSpringSecurityWithJwt.jwt.JwtUser;
import com.example.testSpringSecurityWithJwt.mapper.UserMapper;
import com.example.testSpringSecurityWithJwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/28
 */
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    // 装载BCrypt密码编码器
    @Autowired
    public PasswordEncoder passwordEncoder;


    @Override
    public String login(String username, String password) {
        String token;
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            return null ;
        }
        token = JwtTokenUtils.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        String username = JwtTokenUtils.getUsernameFromToken(oldToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return JwtTokenUtils.generateToken(userDetails);
    }
}
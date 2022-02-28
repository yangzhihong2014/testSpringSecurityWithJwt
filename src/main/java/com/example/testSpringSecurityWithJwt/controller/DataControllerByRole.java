package com.example.testSpringSecurityWithJwt.controller;

import com.alibaba.fastjson.JSON;
import com.example.testSpringSecurityWithJwt.mapper.UserMapper;
import com.example.testSpringSecurityWithJwt.model.ResultData;
import com.example.testSpringSecurityWithJwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/24
 *
 * 数据控制 根据角色
 */
@RestController
@RequestMapping("/data")
public class DataControllerByRole {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping({"/list"})
    @PreAuthorize("hasAnyRole('ROLE_super')")
    public String list(){
        try{
            ResultData resultData = ResultData.getInitSuccess() ;
            List<User> list = userMapper.list();
            resultData.getData().put("list",list) ;
            return JSON.toJSONString(resultData) ;
        }catch (Exception e){
            ResultData resultData = ResultData.getInitFail();
            resultData.setMessage("list失败!");
            return JSON.toJSONString(resultData) ;
        }
    }

    @RequestMapping({"/findByName"})
    @PreAuthorize("hasAnyRole('ROLE_super')")
    public String findByName(@RequestBody User parma){
        try{
            ResultData resultData = ResultData.getInitSuccess() ;
            User user = userMapper.findByUsername(parma.getUsername());
            resultData.getData().put("user",user) ;
            return JSON.toJSONString(resultData) ;
        }catch (Exception e){
            ResultData resultData = ResultData.getInitFail();
            resultData.setMessage("findByName失败!");
            return JSON.toJSONString(resultData) ;
        }
    }

}
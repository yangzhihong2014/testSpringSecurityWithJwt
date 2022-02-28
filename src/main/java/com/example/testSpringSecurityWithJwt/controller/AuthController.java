package com.example.testSpringSecurityWithJwt.controller;

import com.alibaba.fastjson.JSON;
import com.example.testSpringSecurityWithJwt.model.ResultData;
import com.example.testSpringSecurityWithJwt.model.User;
import com.example.testSpringSecurityWithJwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/28
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    AuthService authService ;

    @RequestMapping("/login")
    public String login(@RequestBody User user){
        try{
            ResultData resultData = ResultData.getInitSuccess();
            String login = authService.login(user.getUsername(), user.getPassword());
            if(login == null){
                resultData.setSuccess(false);
                resultData.setMessage("登陆失败!");
            }else{
                resultData.setData("token",login);
            }
            return JSON.toJSONString(resultData) ;
        }catch (Exception e){
            ResultData resultData = ResultData.getInitFail();
            resultData.setMessage("登陆失败!");
            return JSON.toJSONString(resultData) ;
        }

    }


    @RequestMapping("/refresh")
    public String refresh(HttpServletRequest request){
        try{
            ResultData resultData = ResultData.getInitSuccess();
            String token = request.getHeader("token");
            String refresh = authService.refresh(token);

            if(refresh == null){
                resultData.setSuccess(false);
                resultData.setMessage("refresh失败!");
            }else{
                resultData.setData("token",refresh);
            }
            return JSON.toJSONString(resultData) ;
        }catch (Exception e){
            ResultData resultData = ResultData.getInitFail();
            resultData.setMessage("refresh失败!");
            return JSON.toJSONString(resultData) ;
        }
    }
}
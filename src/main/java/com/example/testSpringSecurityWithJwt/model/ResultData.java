package com.example.testSpringSecurityWithJwt.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/28
 */
public class ResultData {
    private boolean success ;
    private String message ;
    private Map<String,Object> data ;

    public ResultData() {
    }

    public ResultData(boolean success, String message, Map<String, Object> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static ResultData getInitSuccess(){
        return new ResultData(true,null,new HashMap<>()) ;
    }

    public static ResultData getInitFail(){
        return new ResultData(false,null,new HashMap<>()) ;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(String key ,Object value) {
        this.data.put(key,value);
    }
}
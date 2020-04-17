package com.xzh.springbootshiroredissession.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 自定义异常
 *
 */
@ControllerAdvice
public class MyShiroException {

    /**
     *
     * 处理Shiro权限拦截异常
     *
     * AuthorizationException 授权异常
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String,Object> defaultErrorHandler(){
        Map<String,Object> map = new HashMap<>();
        map.put("403","权限不足");
        return map;
    }

}

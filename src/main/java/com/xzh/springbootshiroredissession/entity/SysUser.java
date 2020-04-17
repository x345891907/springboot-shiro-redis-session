package com.xzh.springbootshiroredissession.entity;

import java.io.Serializable;

/**
 * 系统用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2020-04-14 14:45:52
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = -74077315623774616L;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 盐值
    */
    private String salt;
    /**
    * 状态:NORMAL正常  PROHIBIT禁用
    */
    private String state;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
package com.xzh.springbootshiroredissession.entity;

import java.io.Serializable;

/**
 * 用户与角色关系表(SysUserRole)实体类
 *
 * @author makejava
 * @since 2020-04-14 14:45:52
 */
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -61200726122888442L;
    /**
    * ID
    */
    private Long id;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 角色ID
    */
    private Long roleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
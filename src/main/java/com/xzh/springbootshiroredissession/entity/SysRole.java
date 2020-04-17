package com.xzh.springbootshiroredissession.entity;

import java.io.Serializable;

/**
 * 角色表(SysRole)实体类
 *
 * @author makejava
 * @since 2020-04-14 14:45:51
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = -51442858708275626L;
    /**
    * 角色ID
    */
    private Long roleId;
    /**
    * 角色名称
    */
    private String roleName;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
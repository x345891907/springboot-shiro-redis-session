package com.xzh.springbootshiroredissession.entity;

import java.io.Serializable;

/**
 * 角色与权限关系表(SysRoleMenu)实体类
 *
 * @author makejava
 * @since 2020-04-14 14:45:51
 */
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 878916753033101995L;
    /**
    * ID
    */
    private Long id;
    /**
    * 角色ID
    */
    private Long roleId;
    /**
    * 权限ID
    */
    private Long menuId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}
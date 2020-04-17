package com.xzh.springbootshiroredissession.entity;

import java.io.Serializable;

/**
 * 权限表(SysMenu)实体类
 *
 * @author makejava
 * @since 2020-04-14 14:45:47
 */
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 683798488563465659L;
    /**
    * 权限ID
    */
    private Long menuId;
    /**
    * 权限名称
    */
    private String name;
    /**
    * 权限标识
    */
    private String perms;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

}
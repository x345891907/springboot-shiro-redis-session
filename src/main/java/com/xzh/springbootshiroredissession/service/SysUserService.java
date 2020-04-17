package com.xzh.springbootshiroredissession.service;

import com.xzh.springbootshiroredissession.entity.SysUser;

import java.util.List;

/**
 * 系统用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2020-04-14 14:45:52
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUser queryById(Long userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

    /**
     * 根据用户名查询实体
     * @Param  username 用户名
     * @Return SysUser 用户实体
     */
    SysUser selectUserByName(String username);

}
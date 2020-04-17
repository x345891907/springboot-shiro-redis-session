package com.xzh.springbootshiroredissession.service.impl;

import com.xzh.springbootshiroredissession.dao.SysMenuDao;
import com.xzh.springbootshiroredissession.entity.SysMenu;
import com.xzh.springbootshiroredissession.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-04-14 14:45:50
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public SysMenu queryById(Long menuId) {
        return this.sysMenuDao.queryById(menuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysMenu> queryAllByLimit(int offset, int limit) {
        return this.sysMenuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public SysMenu insert(SysMenu sysMenu) {
        this.sysMenuDao.insert(sysMenu);
        return sysMenu;
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public SysMenu update(SysMenu sysMenu) {
        this.sysMenuDao.update(sysMenu);
        return this.queryById(sysMenu.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long menuId) {
        return this.sysMenuDao.deleteById(menuId) > 0;
    }

    /**
     * 根据角色查询用户权限
     * @Param  roleId 角色ID
     * @Return List<SysMenu> 权限集合
     */
    @Override
    public List<SysMenu> selectSysMenuByRoleId(Long roleId) {

        return sysMenuDao.selectSysMenuByRoleId(roleId);
    }
}
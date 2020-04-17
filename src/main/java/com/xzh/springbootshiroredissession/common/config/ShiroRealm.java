package com.xzh.springbootshiroredissession.common.config;

import com.xzh.springbootshiroredissession.common.util.ShiroUtils;
import com.xzh.springbootshiroredissession.entity.SysMenu;
import com.xzh.springbootshiroredissession.entity.SysRole;
import com.xzh.springbootshiroredissession.entity.SysUser;
import com.xzh.springbootshiroredissession.service.SysMenuService;
import com.xzh.springbootshiroredissession.service.SysRoleService;
import com.xzh.springbootshiroredissession.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Realm用于授权和认证
 *
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     *
     * 授权权限
     * 用户进行权限验证时候Shiro会去缓存中找,如果查不到数据,会执行这个方法去查权限,并放入缓存中
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权权限");
        //获取用户ID
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUserEntity = (SysUser) principals.getPrimaryPrincipal();
        Long userId =sysUserEntity.getUserId();
        //这里可以进行授权和处理
        Set<String> rolesSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();
        //查询角色和权限(这里根据业务自行查询)
        List<SysRole> sysRoleEntityList = sysRoleService.selectSysRoleByUserId(userId);
        for (SysRole sysRoleEntity:sysRoleEntityList) {
            rolesSet.add(sysRoleEntity.getRoleName());
            List<SysMenu> sysMenuEntityList = sysMenuService.selectSysMenuByRoleId(sysRoleEntity.getRoleId());
            for (SysMenu sysMenuEntity :sysMenuEntityList) {
                permsSet.add(sysMenuEntity.getPerms());
            }
        }
        //将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(rolesSet);
        return authorizationInfo;
    }


    /**
     *
     * 身份认证
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("身份认证");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到进行验证
        //实际项目中,这里可以根据实际情况做缓存,如果不做,Shiro自己也是有时间间隔机制,2分钟内不会重复执行该方法
        SysUser user = sysUserService.selectUserByName(username);
        //判断账号是否存在
        if (user == null) {
            throw new AuthenticationException();
        }
        //判断账号是否被冻结
        if (user.getState()==null||user.getState().equals("PROHIBIT")){
            throw new LockedAccountException();
        }
        /**
         * 判断密码  使用AuthenticationInfo的子类 SimpleAuthenticationInfo
         *		   第一个 参数 返回 subject.login(token)的一些数据
         *		   第二个参数 数据库的密码，
         *		   第三个参数 shrio的名字
         *
         * 进行验证
         */
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
        //验证成功开始踢人(清除缓存和Session)
        ShiroUtils.deleteCache(username,true);
        return simpleAuthenticationInfo;
    }
}
package com.xzh.springbootshiroredissession.common.util;

import com.xzh.springbootshiroredissession.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 *
 * Shiro工具类
 *
 */
@Slf4j
public class ShiroUtils {

    /** 私有构造器 **/
    private ShiroUtils(){}

    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

    /**
     * 获取当前用户Session
     *
     * @return
     */
    public static Session getSession() {
        log.info("Shiro工具类,获取当前用户Session");
        return SecurityUtils.getSubject().getSession();
    }

    /**
     *
     * 用户登出
     *
     */
    public static void logout() {
        log.info("Shiro工具类,用户登出");
        SecurityUtils.getSubject().logout();
    }

    /**
     *
     * 获取当前用户信息
     *
     * @return
     */
    public static SysUser getUserInfo() {
        log.info("Shiro工具类,获取当前用户信息");
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     *
     * 删除用户缓存信息
     *
     */
    public static void deleteCache(String username, boolean isRemoveSession){
        log.info("Shiro工具类,删除用户缓存信息");
        //从缓存中获取Session
        Session session = null;
        //从redis中拿到所有的session

        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        SysUser sysUserEntity;
        Object attribute = null;
        for(Session sessionInfo : sessions){
            //遍历Session,找到该用户名称对应的Session
            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) {
                continue;
            }
            sysUserEntity = (SysUser) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (sysUserEntity == null) {
                continue;
            }
            if (Objects.equals(sysUserEntity.getUsername(), username)) {
                session=sessionInfo;
                break;
            }
        }
        if (session == null||attribute == null) {
            return;
        }
        //删除session
        if (isRemoveSession) {
            redisSessionDAO.delete(session);
        }
        //删除Cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authc = securityManager.getAuthenticator();
        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
    }

}

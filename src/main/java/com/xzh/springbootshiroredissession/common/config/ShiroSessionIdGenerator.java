package com.xzh.springbootshiroredissession.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 *
 * 自定义SessionId生成器
 *
 */
@Slf4j
public class ShiroSessionIdGenerator implements SessionIdGenerator {

    /**
     * 实现SessionId生成
     * @param session
     * @return
     */
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId  = new JavaUuidSessionIdGenerator().generateId(session);
        log.info("自定义SessionId生成器={}",sessionId.toString());
        return String.format("login_token_%s",sessionId);
    }
}

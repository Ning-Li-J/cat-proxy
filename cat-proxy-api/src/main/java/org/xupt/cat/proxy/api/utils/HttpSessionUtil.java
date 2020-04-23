package org.xupt.cat.proxy.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.xupt.cat.proxy.api.constant.HttpSessionConstant;
import org.xupt.cat.proxy.api.exception.NonLoginException;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-11 下午6:01
 */
@Slf4j
public class HttpSessionUtil {

    private static ThreadLocal<HttpSession> local = new ThreadLocal();

    public static void setHttpSession(HttpSession session) {
        local.set(session);
    }

    public static String getSessionId() {
        Object sessionId = getValue(HttpSessionConstant.JSESSIONID);
        return sessionId.toString();
    }

    public static void setSessionId(String sessionId) {
        setValue(HttpSessionConstant.JSESSIONID, sessionId);
    }

    public static void invalidate() {
        HttpSession session = local.get();
        if (Objects.isNull(session)) {
            return;
        }

        session.invalidate();
    }

    private static Object getValue(String key) {
        HttpSession session = local.get();
        if (Objects.isNull(session)) {
            throw new NonLoginException("Auth授权失败, Session Is Empty");
        }

        Object o = session.getAttribute(key);
        if (Objects.isNull(o)) {
            throw new NonLoginException("获取Auth信息失败, value Is Empty, key :" + key);
        }

        log.info("Session util get value. key :{}, value :{}.", key, o);
        return o;
    }

    private static void setValue(String key, Object value) {
        if (Objects.isNull(value)) {
            return;
        }

        HttpSession session = local.get();
        if (Objects.isNull(session)) {
            throw new NonLoginException("Auth授权失败, Session Is Empty");
        }

        log.info("Session util set value. key :{}, value :{}.", key, value);
        session.setAttribute(key, value);
    }

}

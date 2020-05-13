package org.xupt.cat.proxy.api.controller.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.xupt.cat.proxy.api.constant.HttpSessionConstant;
import org.xupt.cat.proxy.api.utils.HttpSessionUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 获取Session，并放入到ThreadLocal中
 *
 * @author lining
 * @data 2020/1/2
 */
@Component
@Slf4j
public class HttpSessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ThreadContext.put("logId", System.currentTimeMillis() % 1000000 + "");

        log.info("Get http session start...");
        HttpSession session = null;

        String uri = request.getRequestURI();
        if (HttpSessionConstant.CREATE_SESSION_URL.equalsIgnoreCase(uri)) {
            log.info(":{} is login url, so need create session!", uri);
            session = request.getSession(true);

            Cookie cookie = new Cookie(HttpSessionConstant.JSESSIONID, session.getId() + "");
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

        } else {
            session = request.getSession(false);
        }

        HttpSessionUtil.setHttpSession(session);
        log.info("Set http session end...");
        return true;
    }
}

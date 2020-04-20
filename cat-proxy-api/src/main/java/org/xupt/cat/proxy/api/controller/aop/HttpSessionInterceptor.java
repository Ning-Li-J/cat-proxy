package org.xupt.cat.proxy.api.controller.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.xupt.cat.proxy.api.utils.HttpSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 获取Session，并放入到ThreadLocal中
 * @author lining
 * @data 2020/1/2
 */
@Component
@Slf4j
public class HttpSessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Get http session start...");
        HttpSession session = request.getSession(true);
        HttpSessionUtil.setHttpSession(session);
        log.info("Set http session end...");
        return true;
    }
}

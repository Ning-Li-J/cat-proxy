package org.xupt.cat.proxy.api.controller.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.xupt.cat.proxy.api.controller.annocation.Auch;
import org.xupt.cat.proxy.api.exception.NonLoginException;
import org.xupt.cat.proxy.api.utils.HttpSessionUtil;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 利用aop，对接口权限校验
 *
 * @author lining
 * @data 2020-04-15 下午4:38
 */
@Slf4j
@Aspect
@Component
public class AuchCheckAdvice {

    @Pointcut("execution(* org.xupt.cat.proxy.api.controller.*.*(..))")
    public void auchCheck() {
    }

    @Before("auchCheck()")
    public void doBefore(JoinPoint jp) {
        log.info("Auch check star...");

        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Auch[] auchs = method.getAnnotationsByType(Auch.class);

        //接口不需要权限
        if (Objects.isNull(auchs) || auchs.length == 0) {
            log.info("Auch check. method :{} not need auch", method.getName());
            return;
        }

        //校验权限
        String sessionId = HttpSessionUtil.getSessionId();
        if (StringUtils.isEmpty(sessionId)) {
            throw new NonLoginException("未登录，请先登录");
        }

        log.info("Auch check end...");
    }
}

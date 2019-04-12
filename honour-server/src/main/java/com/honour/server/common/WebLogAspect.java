package com.honour.server.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

    private ThreadLocal<Long> timeLocal = new ThreadLocal<>();

    @Pointcut("execution(public * com.honour.server.web.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        timeLocal.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            log.info("URL：" + request.getRequestURL().toString());
            log.info("HTTP_METHOD：" + request.getMethod());
            log.info("IP：" + request.getRemoteAddr());
            log.info("CLASS_METHOD：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.info("ARGS：" + Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("RESPONSE：" + ret);
        log.info("SPEND TIME：" + (System.currentTimeMillis() - timeLocal.get()));
    }
}

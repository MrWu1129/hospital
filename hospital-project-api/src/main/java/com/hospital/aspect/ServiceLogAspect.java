package com.hospital.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: hospital-project
 * @description: 环绕通知日志监控
 * @author: wty
 * @create: 2020-05-15 20:11
 */
@Aspect
@Component
public class ServiceLogAspect {

    private static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Around("execution(* com.hospital.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("====== 开始执行 ====== {}.{}",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        // 开始执行时间
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        // 执行结束时间
        long end = System.currentTimeMillis();
        long diff = end - begin;
        if (diff > 3000) {
            log.error("====== 执行结束，用时{}毫秒 ======", diff);
        } else if (diff > 2000) {
            log.warn("====== 执行结束，用时{}毫秒 ======", diff);
        } else {
            log.info("====== 执行结束，用时{}毫秒 ======", diff);
        }
        return result;
    }

}

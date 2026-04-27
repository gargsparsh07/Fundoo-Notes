package com.bridgelabz.fundoonotes.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.bridgelabz.fundoonotes.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        log.info("Method [{}] executed in {} ms",
                joinPoint.getSignature().getName(), duration);
        return result;
    }

    @Before("execution(* com.bridgelabz.fundoonotes.service..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.debug("Calling service method: {}",
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(
            pointcut = "execution(* com.bridgelabz.fundoonotes.service..*(..))",
            throwing = "ex")
    public void logAfterException(JoinPoint joinPoint, Exception ex) {
        log.error("Exception in [{}]: {}",
                joinPoint.getSignature().getName(), ex.getMessage());
    }
}
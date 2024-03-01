package com.example.demomongo.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.Arrays;

@Aspect
@Log4j2
@Component
@RequiredArgsConstructor
public class LogAspect {

    // 패키지 범위 설정
    @Pointcut("execution(* com.example..*Controller.*(..))")
    public void controllerMethods() {
    }

    // 메소드 실행 전
    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("request {}", joinPoint.getSignature().toShortString());
        Arrays.stream(joinPoint.getArgs()).map(Object::toString).map(str -> "\t" + str).forEach(log::info);
    }

    // 메소드 리턴 값 반환 후
    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("response {}", joinPoint.getSignature().toShortString());
        if (result == null) return;
        log.info("\t{}", result.toString());
    }

    @AfterThrowing(pointcut = "controllerMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) throws UnknownHostException {
        String methodName = joinPoint.getSignature().toShortString();
        String errorMsg = exception.getMessage();
        log.error("error {}", methodName);
        log.error("\t errorMsg: {}", errorMsg);
    }
}

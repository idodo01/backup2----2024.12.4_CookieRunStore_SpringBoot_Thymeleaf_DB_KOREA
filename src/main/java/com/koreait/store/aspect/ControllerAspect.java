package com.koreait.store.aspect;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class ControllerAspect {
    // 컨트롤러의 메서드가 실행될 때 실행되는 로그
    @Before("execution(* com.koreait.store.controller.*.*(..))")
    public void method_start_log(JoinPoint joinPoint){
        log.info("==== " +joinPoint.getSignature().getName() + " ====");
        Object[] objects = joinPoint.getArgs();
        for(Object object : objects){
            log.info(object);
        }
    }
}

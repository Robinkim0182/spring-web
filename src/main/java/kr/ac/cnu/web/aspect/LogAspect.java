package kr.ac.cnu.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by rokim on 2018. 5. 25..
 */
//@Aspect
@Slf4j
//@Component
public class LogAspect {
    /**
     * SEE
     * http://www.baeldung.com/spring-aop-pointcut-tutorial
     * https://docs.spring.io/spring/docs/2.0.x/reference/aop.html
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* kr.ac.cnu.web.repository.*.* (..))")
//    @Around("execution(* kr.ac.cnu.web..* (..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        long before = System.currentTimeMillis();

        Object obj = joinPoint.proceed();

        long after = System.currentTimeMillis();

        log.info("{}.{} 이 걸린 시간은 {} millisecond 입니다.", signature.getDeclaringTypeName(), signature.getMethod().getName(), (after - before));
        return obj;
    }
}

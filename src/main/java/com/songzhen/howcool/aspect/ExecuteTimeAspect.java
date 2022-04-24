package com.songzhen.howcool.aspect;

import com.songzhen.howcool.annotation.ExecuteTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExecuteTimeAspect {

    private final Logger logger = LoggerFactory.getLogger(ExecuteTimeAspect.class);

    @Pointcut("@annotation(executeTime)")
    public void serviceExecutionTimeLog(ExecuteTime executeTime) {
    }


    @Around(value = "serviceExecutionTimeLog(executeTime)", argNames = "proceedingJoinPoint,executeTime")
    public Object doAfter(ProceedingJoinPoint proceedingJoinPoint, ExecuteTime executeTime) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = proceedingJoinPoint.proceed();
        stopWatch.stop();

        logger.info("execute-time-name : [{}], execution-time : [{}], class-method : [{}]", executeTime.name(),
                stopWatch.getTotalTimeMillis(),
                proceedingJoinPoint.getTarget().getClass().getName() + "." + proceedingJoinPoint.getSignature().getName());

        return proceed;
    }
}
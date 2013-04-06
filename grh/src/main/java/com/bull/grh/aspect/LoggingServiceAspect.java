package com.bull.grh.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.Serializable;

/**
 * @author simu
 */
@Aspect
@Component
public class LoggingServiceAspect implements Serializable {

    private static final long serialVersionUID = 1L;
    protected final Logger logger = Logger.getLogger("Service LOG");

    @Around("execution(* com.bull.grh.service..*(..))")
    public Object serviceLogAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object retVal = joinPoint.proceed();

        stopWatch.stop();
        StringBuffer logMessage = new StringBuffer();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());

        logMessage.append(" execution time: ");
        logMessage.append(stopWatch.getTotalTimeMillis());
        logMessage.append(" ms");
        logger.info(logMessage.toString());
        return retVal;
    }

    @Before("execution(* com.bull.grh.service..*(..))")
    public void beforeService(JoinPoint joinPoint) {

        StringBuffer logMessage = new StringBuffer();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        // append args
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }

        logMessage.append(")");
        logger.info(logMessage.toString());
    }

    @AfterReturning(pointcut = "execution(* com.bull.grh.service..*(..))", returning = "result")
    public void afterService(JoinPoint joinPoint, Object result) {

        StringBuffer logMessage = new StringBuffer();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append(" return : " + result);
        logger.info(logMessage.toString());
    }
}

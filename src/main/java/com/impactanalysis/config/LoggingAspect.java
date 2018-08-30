package com.impactanalysis.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Before("execution(* com.impactanalysis.*.*.*(..))")
    public void before(JoinPoint joinPoint) {
    	logger.info(String.format("Target=%s, ClassFile=%s", joinPoint.getTarget(), 
    			joinPoint.getTarget().getClass()!=null ? joinPoint.getTarget().getClass().getName() : ""));
    }
}

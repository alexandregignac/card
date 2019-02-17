package com.game.cardgame.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.aspectj.lang.annotation.Around;

@Aspect
@Configuration
public class ExceptionAspect {

	@Around("bean(*Controller)")
	public Object catchException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object value = null;
		try {
            value = proceedingJoinPoint.proceed();
        } catch (Exception e) {  
            //Assume a call to a logging system
            throw new Exception("pretty exception");
        }
		return value;
	}

}
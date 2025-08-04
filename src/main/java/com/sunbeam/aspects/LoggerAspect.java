package com.sunbeam.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect // to declare a class containing cross cutting concerns
@Component // => spring bean , singleton n eager
@Slf4j//lombok annotation to add logger field
public class LoggerAspect {
	//before ANY method exec - intercept
	@Before("execution (* com.sunbeam.controller.*.*(..))")
	public void createLog(JoinPoint joinpoint)
	{
		log.info("------Intercepting method exec before {} ------------"
				, joinpoint);
	}
}

package com.mdesb.config;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Configuration
@Aspect
public class AOPConfig {

	// 声明表达式，描述织入特性
	@Around("@within(org.springframework.stereotype.Controller)")
	public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable {
		try {
			Object o = pjp.proceed();
//			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();  
//	        //从获取RequestAttributes中获取HttpServletRequest的信息  
//	        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);  
			return o;
		} catch (Throwable e) {
			throw e;
		}
	}

}

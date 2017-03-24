package com.ucloudlink.aop;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.ucloudlink.ProviderEurekaController;

import io.swagger.models.Swagger;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;


@Component
@Aspect
public class MyInterceptor {

	@Autowired
	private ProviderEurekaController controller;

	@Pointcut("execution(* org.springframework.boot.StartupInfoLogger.getStartedMessage(..))")
	private void anyMethod() {

	}// 定义一个切入点

    
	@Around("anyMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		
		Object object = pjp.proceed();// 执行该方法
		
		controller.addDocs();
		
		return object;
	}
	

	
}

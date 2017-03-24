package zuulserver.aop;

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

import io.swagger.models.Swagger;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import zuulserver.configuration.SwaggerCache;

@Component
@Aspect
public class MyInterceptor {

	@Autowired
	private SwaggerCache swaggerCache;

	@Value("${springfox.documentation.swagger.v2.path:/v2/api-docs}")
	private String swagger2Url;

	@Pointcut("execution(* springfox.documentation.swagger2.web.Swagger2Controller.*(..))")
	private void anyMethod() {

	}// 定义一个切入点

	@Pointcut("execution(* springfox.documentation.swagger.web.ApiResourceController.swaggerResources(..))")
	private void resMethod() {

	}//

	@Around("anyMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("进入环绕通知");
		String groupName = Optional.fromNullable((String) pjp.getArgs()[0]).or(Docket.DEFAULT_GROUP_NAME);

		Json swag = swaggerCache.getCache(groupName);

		if (swag != null) {
			return new ResponseEntity<Json>(swag, HttpStatus.OK);
		}

		Object object = pjp.proceed();// 执行该方法
		System.out.println("退出方法");
		return object;
	}

	@Around("resMethod()")
	public Object doResProfiling(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("进入环绕通知");

		Object object = pjp.proceed();// 执行该方法

		ResponseEntity<List<SwaggerResource>> result = (ResponseEntity<List<SwaggerResource>>) object;
		List<SwaggerResource> swaggers = result.getBody();

		Map<String, Json> cachMap = swaggerCache.getMap();

		for (String groupName : cachMap.keySet()) {
			SwaggerResource swaggerResource = resource(groupName, swagger2Url);
			swaggerResource.setSwaggerVersion("2.0");
			
			swaggers.add(swaggerResource);
		}

		System.out.println("退出方法");
		return object;
	}

	private SwaggerResource resource(String swaggerGroup, String baseUrl) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(swaggerGroup);
		swaggerResource.setLocation(swaggerLocation(baseUrl, swaggerGroup));
		return swaggerResource;
	}

	private String swaggerLocation(String swaggerUrl, String swaggerGroup) {
		String base = Optional.of(swaggerUrl).get();
		if (Docket.DEFAULT_GROUP_NAME.equals(swaggerGroup)) {
			return base;
		}
		return base + "?group=" + swaggerGroup;
	}

}

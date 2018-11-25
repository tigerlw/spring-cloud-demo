package com.camp.swagger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.camp.domain.ResInput;
import com.camp.domain.SwaggerInput;

import io.swagger.models.Swagger;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

@Component
@ConditionalOnBean(DocumentationPluginsBootstrapper.class)
public class InitRemoteSwagger implements SmartLifecycle, ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private DocumentationCache documentationCache;

	@Autowired
	private RestTemplate restTemplate;
	
    
    @Autowired
    private ServiceModelToSwagger2Mapper mapper;
    
    @Autowired
	private JsonSerializer jsonSerializer;

	@Override
	public void start() {
		// TODO Auto-generated method stub
		// System.out.println(documentationCache.documentationByGroup("test"));
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPhase() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isAutoStartup() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub

		
		if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.  
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。  
      
		
	    Documentation documentation = documentationCache.documentationByGroup("test");

		Swagger swagger = mapper.mapDocumentation(documentation);

		SwaggerInput swaggerInput = new SwaggerInput();

		swaggerInput.setGroupName("test");
		swaggerInput.setSwagger(jsonSerializer.toJson(swagger).value());

		String result = restTemplate.postForEntity("http://api-gateway/addDoc", swaggerInput, String.class).getBody();
		
		System.out.println(result);
		
		 }  
	}

}

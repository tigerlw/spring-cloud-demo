package zuulserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucloudlink.utils.SerializeTool;

import io.swagger.models.Swagger;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import zuulserver.configuration.SwaggerCache;
import zuulserver.domain.SwaggerInput;

@RestController
public class GatewayController 
{
	
	@Autowired
	private SwaggerCache swaggerCache;
	
	@RequestMapping(value = "/addDoc" ,method = RequestMethod.POST)
	public @ResponseBody String addDocuments(@RequestBody SwaggerInput swaggerInput)
	{
		
		swaggerCache.putCache(swaggerInput.getGroupName(), new Json(swaggerInput.getSwagger()));
	
		return "success";
	}



}

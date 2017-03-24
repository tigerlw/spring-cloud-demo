package zuulserver.configuration;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.swagger.models.Swagger;
import springfox.documentation.spring.web.json.Json;

public class SwaggerCache {
	
	private static Map<String,Json> cachMap = new ConcurrentHashMap<String,Json>();
	
	public void putCache(String groupName,Json swagger)
	{
		cachMap.put(groupName, swagger);
	}
	
	public Json getCache(String groupName)
	{
		Json result = cachMap.get(groupName);
		
		return result;
	}
	
	public Map<String,Json> getMap()
	{
		return cachMap;
	}

}

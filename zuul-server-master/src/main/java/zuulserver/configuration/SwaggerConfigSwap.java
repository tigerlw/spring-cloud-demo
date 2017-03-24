package zuulserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigSwap {
	
	@Bean
	public SwaggerCache createSwaggerMap()
	{
		return new SwaggerCache();
	}

}

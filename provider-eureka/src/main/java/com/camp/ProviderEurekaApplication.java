package com.camp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//import de.codecentric.boot.admin.config.EnableAdminServer;

//import de.codecentric.boot.admin.config.EnableAdminServer;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
//@EnableAdminServer
public class ProviderEurekaApplication {

	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ProviderEurekaApplication.class, args);
		
		
		ProviderEurekaController controller = context.getBean(ProviderEurekaController.class);
		controller.addDocs();
		
		System.out.println("================================================================");
	}

}

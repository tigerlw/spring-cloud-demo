package com.ucloudlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ComsumerFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComsumerFeignApplication.class, args);
	}

}

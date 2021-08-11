package com.housepass.imoveis.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackages = "com.housepass.imoveis.app.entities")
public class MsHousepassImoveisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHousepassImoveisApplication.class, args);
	}
	
 
	
}

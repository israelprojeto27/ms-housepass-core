package com.housepass.eureka.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MsHousepassEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHousepassEurekaServerApplication.class, args);
	}

}

package com.housepass.timeline.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class MsHousepassTimelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHousepassTimelineApplication.class, args);
	}

}

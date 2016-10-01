package org.pollux.cloud.compute.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudComputeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudComputeServiceApplication.class, args);
	}
}

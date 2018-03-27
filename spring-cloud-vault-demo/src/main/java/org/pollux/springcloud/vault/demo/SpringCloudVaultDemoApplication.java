package org.pollux.springcloud.vault.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCloudVaultDemoApplication {

	@RequestMapping("/")
	public String home() {
		return "hello, world";
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringCloudVaultDemoApplication.class, args);
	}
}

package org.pollux.ssldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SslDemoApplication {

	@GetMapping("/hello")
	public String helloSSL() {
		return "ssl";
	}

	public static void main(String[] args) {
		SpringApplication.run(SslDemoApplication.class, args);
	}
}

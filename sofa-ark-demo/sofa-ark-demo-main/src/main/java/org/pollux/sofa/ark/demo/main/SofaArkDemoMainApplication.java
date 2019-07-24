package org.pollux.sofa.ark.demo.main;

import com.alipay.sofa.ark.support.startup.SofaArkBootstrap;
import org.pollux.sofa.ark.demo.service.controller.ServiceAv1;
import org.pollux.sofa.ark.demo.service.controller.ServiceAv2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SofaArkDemoMainApplication {

	public static void main(String[] args) {

		SofaArkBootstrap.launch(args);
		System.out.println("---------------------------");
		System.out.println("main classloader is " + SofaArkDemoMainApplication.class.getClassLoader());
		System.out.println("----------------------------");

		new ServiceAv1().test();
		new ServiceAv2().test();

		SpringApplication.run(SofaArkDemoMainApplication.class, args);
	}

}

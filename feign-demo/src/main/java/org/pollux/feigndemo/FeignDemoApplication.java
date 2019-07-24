package org.pollux.feigndemo;

import org.pollux.feigndemo.controller.Employee;
import org.pollux.mystarter.log.annotation.EnableLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableLogFilter
public class FeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignDemoApplication.class, args);

//        Employee e = new Employee();
//        e.setName("1");
//        e.setSalary(12.1);
//
//        replaceEmployee(e);
//        System.out.println(e.getName());
    }

    public static void replaceEmployee(Employee e) {
        e = new Employee();
        e.setName("employee");
        e.setSalary(12.1);
    }
}

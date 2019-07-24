package org.spockwang.arkuseplugindemo;

import org.spockwang.arkdemo.hessian3.Hessian3Service;
import org.spockwang.arkdemo.hessian4.Hessian4Service;
import org.spockwang.arkdemo.pojo.SamplePoJo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
public class ArkUserpluginDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArkUserpluginDemoApplication.class, args);
    }

    @RequestMapping("/hessian3")
    public String hessian3() throws IOException {
        SamplePoJo samplePoJo = new SamplePoJo("hello, hassian3");
        Hessian3Service hessian3Service = new Hessian3Service();
        byte[] bytes = hessian3Service.serialize(samplePoJo);
        Object pojo = hessian3Service.deserialize(bytes);
        return pojo.toString();
    }

    @RequestMapping("/hessian4")
    public String hessian4() throws Exception {
        try{
        SamplePoJo samplePoJo = new SamplePoJo("hello, hassian4");
        Hessian4Service hessian4Service = new Hessian4Service();
        byte[] bytes = hessian4Service.serialize(samplePoJo);
        Object pojo = hessian4Service.deserialize(bytes);
        hessian4Service.test();
        return pojo.toString();
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

    }

}

package com.zhou;


import com.hui.myrule.MySelfRule;
import com.zhou.lb.MyLB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "cloud-payment-service",configuration = MySelfRule.class)
public class CloudCommonsOrder80Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudCommonsOrder80Application.class, args);
    }

}

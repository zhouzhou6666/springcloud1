package com.zhou;

import com.zhou.service.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CloudalibbaConsumerNacosOrder84Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudalibbaConsumerNacosOrder84Application.class, args);
    }

}

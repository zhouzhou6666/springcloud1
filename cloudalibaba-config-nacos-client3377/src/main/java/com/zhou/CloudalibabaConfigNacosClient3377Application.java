package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudalibabaConfigNacosClient3377Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudalibabaConfigNacosClient3377Application.class, args);
    }

}

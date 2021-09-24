package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudProviderPayment8004Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPayment8004Application.class, args);
    }

}

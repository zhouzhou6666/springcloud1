package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudStreamRabbitmqProvider8803Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudStreamRabbitmqProvider8803Application.class, args);
    }

}

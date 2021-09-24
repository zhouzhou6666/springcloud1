package com.zhou.cotroller;



import com.zhou.entities.CommonResult;
import com.zhou.entities.Payment;
import com.zhou.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class OrderConotroller {

    public static final String PAYMENT_URL="http://cloud-payment-service";

    @Resource
    RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        System.out.println(payment);
        log.info("消费者插入信息");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        log.info("消费者id查询");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances=discoveryClient.getInstances("cloud-payment-service");
        if(instances==null||instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance=loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);

    }
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String result=restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin",String.class);
        return result;
    }
}

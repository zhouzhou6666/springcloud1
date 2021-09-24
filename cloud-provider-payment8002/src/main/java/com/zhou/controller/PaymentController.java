package com.zhou.controller;


import com.zhou.entities.CommonResult;
import com.zhou.entities.Payment;
import com.zhou.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "payment/create")
    public CommonResult create(@RequestBody Payment payment){
        System.out.println(payment);
        int result= paymentService.create(payment);
        log.info("插入结果"+result);
        if(result>0){
            return new CommonResult(200,"数据插入成功,serverPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"数据插入失败",null);
        }
     }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment= paymentService.getPaymentById(id);
        log.info("查询结果"+payment);
        if(payment!=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录，查询ID:"+id,null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services= discoveryClient.getServices();
        for(String service:services){
            log.info("****service:"+service);
        }
        List<ServiceInstance> instances= discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance:instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
    @GetMapping("/payment/feign/timeout")
    public  String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}

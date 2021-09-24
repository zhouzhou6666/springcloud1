package com.zhou.controller;

import com.zhou.entities.CommonResult;
import com.zhou.entities.Payment;
import com.zhou.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFergnService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFergnService.getPaymentById(id);
    }

    @GetMapping("/consumer//payment/feign/timeout")
    public  String paymentFeignTimeout(){
        return paymentFergnService.paymentFeignTimeout();
    }
}

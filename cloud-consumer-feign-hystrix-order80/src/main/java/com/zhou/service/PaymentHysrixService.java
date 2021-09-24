package com.zhou.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-hystrix-payment",fallback = PaymentFallbackService.class)
public interface PaymentHysrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String getPaymentInfo_OK(@PathVariable("id") Long id);
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getPaymentInfo_Timeout(@PathVariable("id") Long id);



}

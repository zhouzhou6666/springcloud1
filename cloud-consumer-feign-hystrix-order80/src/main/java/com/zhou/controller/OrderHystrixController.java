package com.zhou.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhou.service.PaymentHysrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHysrixService paymentHysrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String getPaymentInfo_OK(@PathVariable("id") Long id) {
        String result = paymentHysrixService.getPaymentInfo_OK(id);
        return result;
    }



    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties= {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String getPaymentInfo_Timeout(@PathVariable("id") Long id) {
        String result = paymentHysrixService.getPaymentInfo_Timeout(id);
        return result;
    }
    //指定fallback
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Long id){
        return "我是消费者80，对方支付系统繁忙轻10秒钟后再试或者自己运行出错请检查机子，o(╥﹏╥)o";
    }
    //全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "全局异常处理,请稍后再试";
    }
}

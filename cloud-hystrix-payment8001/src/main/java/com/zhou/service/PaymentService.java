package com.zhou.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_ok,id: "+id+"\t"+"😄";
    }
@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties= {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")    //设置超时时间
})

    public String paymentInfo_TimeOut(Integer id){
        int timeNumber=5;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_ok,id: "+id+"\t"+"😄"+" 耗时（秒）"+timeNumber;
    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_TimeOutHandler,id: "+id+"\t"+"o(╥﹏╥)o";

    }
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"), //是否开始服务熔断
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")           //失败率达到多少断路
    })
    public String paymentCircuitBreaker(@PathVariable("id") Long id){
        if(id<0){
            throw new RuntimeException("------id不能为负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Long id){
        return "id不能为负数，请稍后再试，o(╥﹏╥)o；id："+id;
    }
}

package com.zhou.controller;

import com.zhou.entities.CommonResult;
import com.zhou.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class Paymentcontroller {
    @Value("${server.port}")
    private String serverPort;
    public static  HashMap<Long, Payment> hashMap=new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"one","111"));
        hashMap.put(2L,new Payment(2L,"one","222"));
        hashMap.put(3L,new Payment(3L,"one","333"));
    }
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment=hashMap.get(id);
        CommonResult<Payment> result=new CommonResult(200,"from mysql,serverPort:"+serverPort,payment);
        return result;
    }
}

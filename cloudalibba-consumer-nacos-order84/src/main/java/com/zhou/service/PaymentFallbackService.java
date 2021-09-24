package com.zhou.service;

import com.zhou.entities.CommonResult;
import com.zhou.entities.Payment;
import org.springframework.stereotype.Component;



@Component
public class PaymentFallbackService implements PaymentService{

    @Override
    public CommonResult<Payment> PaymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回，---paymentFallbackService",new Payment(id,"error","sss"));
    }
}


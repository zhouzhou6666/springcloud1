package com.zhou.myhander;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhou.entities.CommonResult;
import com.zhou.entities.Payment;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"按客户自定义,global handlerException----1",new Payment(2020L,"serial001","941118"));
    }
    public static CommonResult handlerException1(BlockException exception){
        return new CommonResult(4444,"按客户自定义,global handlerException----2",new Payment(2020L,"serial001","941118"));
    }
}

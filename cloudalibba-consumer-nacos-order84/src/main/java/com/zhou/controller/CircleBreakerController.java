package com.zhou.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhou.entities.CommonResult;
import com.zhou.entities.Payment;
import com.zhou.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    @Value("${service-url.nacos-user-service}")
    private String serviceURL;
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",exceptionsToIgnore = {IllegalArgumentException.class,NullPointerException.class})

    public CommonResult<Payment> fallback(@PathVariable("id")Long id){

        CommonResult<Payment> result=restTemplate.getForObject(serviceURL+"/paymentSQL/"+id,CommonResult.class,id);
        if (id==4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常。。。。。。");
        }else if(result.getData()==null){
            throw new NullPointerException("NullPointerException,该id没队友对应记录，空指针异常");
        }
        return result;

    }
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment=new Payment(id,"null","666");
        return new CommonResult<>(444,"兜底遗产handlerFallback,exception内容 "+e.getMessage(),payment);
    }
    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException){
        Payment payment=new Payment(id,"null","null");
        return new CommonResult<>(445,"blockHandler-serntinel限流,无此流水:blockException "+blockException.getMessage(),payment);
    }
    @Resource
    private PaymentService paymentService;
    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return  paymentService.PaymentSQL(id);
    }
}

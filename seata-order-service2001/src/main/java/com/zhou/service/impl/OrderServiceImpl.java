package com.zhou.service.impl;

import com.zhou.dao.OrderDao;
import com.zhou.domain.Order;
import com.zhou.service.AccountService;
import com.zhou.service.OrderService;
import com.zhou.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;


    @Override
    @GlobalTransactional(name = "zhou-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //新建订单
        log.info("--开始新建订单");
        orderDao.create(order);
        //库存扣减
        log.info("--订单微服务开始调用库存，做数量扣减");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("--订单微服务开始调用库存，库存数量完毕");
        //账户余额扣减
        log.info("--订单微服务开始调用账户，做钱数扣减");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--订单微服务开始调用账户，账户金额扣减完毕");
        // 修改订单状态，从0到1，1代表已完成订单
        log.info("--修改订单状态");
        orderDao.update(order.getUserId(),0);
        log.info("--订单状态修改结束");

        log.info("--下单成功，O(∩_∩)O哈哈~");


    }
}

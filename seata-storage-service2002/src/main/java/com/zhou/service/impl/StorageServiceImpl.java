package com.zhou.service.impl;

import com.zhou.dao.StorageDao;
import com.zhou.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        logger.info("--storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        logger.info("--storage-service中扣减库存结束");

    }
}

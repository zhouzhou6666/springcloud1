package com.zhou.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zhou.dao")
public class MyBatisConfig {
}

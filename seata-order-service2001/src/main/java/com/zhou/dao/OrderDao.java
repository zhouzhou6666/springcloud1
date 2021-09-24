package com.zhou.dao;

import com.zhou.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface OrderDao {
    void create(Order order);
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}

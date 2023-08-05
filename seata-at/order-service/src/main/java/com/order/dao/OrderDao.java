package com.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderDao extends BaseMapper<Order> {
}

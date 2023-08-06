package com.order.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.order.dao.OrderDao;
import com.order.dto.BalanceDto;
import com.order.entity.Order;
import com.order.feign.BalanceFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class OrderService {


    @Resource
    OrderDao orderDao;

    @Resource
    BalanceFeignClient balanceFeignClient;

    /**
     * 下单
     * @param count 金额
     * @param userId 用户id
     */
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void submit(BigDecimal count, String userId) {

        // 下单
        Order order = new Order();
        order.setUserId(userId);
        order.setCount(count);
        order.setFdId(IdWorker.get32UUID());
        orderDao.insert(order);

        // 减余额
        balanceFeignClient.deduct(userId, count);


    }
}

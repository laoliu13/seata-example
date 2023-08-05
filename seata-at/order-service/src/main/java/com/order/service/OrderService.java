package com.order.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.order.dao.OrderDao;
import com.order.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {


    @Resource
    OrderDao orderDao;

    /**
     * 下单
     * @param count 金额
     * @param userId 用户id
     */
    public void submit(BigDecimal count, String userId) {

        // TODO 判断余额是否足够

        Order order = new Order();
        order.setUserId(userId);
        order.setCount(count);
        order.setFdId(IdWorker.get32UUID());
        orderDao.insert(order);


    }
}

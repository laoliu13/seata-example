package com.order.controller;

import com.order.service.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 余额controller控制器
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @RequestMapping("/submit/{userId}/{count}")
    public Boolean submit(@PathVariable("userId") String userId, @PathVariable("count") BigDecimal count) {
        orderService.submit(count, userId);
        return true;
    }
}

package com.balance.controller;

import com.balance.dto.BalanceDto;
import com.balance.entity.Balance;
import com.balance.service.BalanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 余额controller控制器
 */
@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Resource
    BalanceService balanceService;

    @RequestMapping("/deduct/{userId}/{count}")
    public Boolean deduct(@PathVariable("userId") String userId, @PathVariable("count") BigDecimal count) {
        balanceService.deduct(userId, count);
        return true;
    }

    @RequestMapping("/insert/{userId}/{count}")
    public Boolean insert(@PathVariable("userId") String userId, @PathVariable("count") BigDecimal count) {
        balanceService.insert(userId, count);
        return true;
    }

    @RequestMapping("/getByUserId/{userId}")
    public BalanceDto getByUserId(@PathVariable("userId") String userId) {
        Balance balance = balanceService.getByUserId(userId);
        BalanceDto balanceDto = new BalanceDto();
        BeanUtils.copyProperties(balance, balanceDto);
        return balanceDto;
    }
}

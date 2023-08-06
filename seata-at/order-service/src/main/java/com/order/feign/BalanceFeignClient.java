package com.order.feign;

import com.order.dto.BalanceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 调用余额
 */
@FeignClient(name = "balance-service")
public interface BalanceFeignClient {

    @RequestMapping("/balance/deduct/{userId}/{count}")
    public Boolean deduct(@PathVariable("userId") String userId, @PathVariable("count") BigDecimal count);

    @RequestMapping("/balance/getByUserId/{userId}")
    public BalanceDto getByUserId(@PathVariable("userId") String userId);

}

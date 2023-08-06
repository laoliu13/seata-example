package com.order.dto;

import java.math.BigDecimal;

public class BalanceDto {

    /**
     * 主键
     */
    private String fdId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 余额
     */
    private BigDecimal count;

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}

package com.balance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

/**
 * 余额表
 */
@TableName("balance_table")
public class Balance {

    /**
     * 主键
     */
    @TableId
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

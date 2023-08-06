package com.balance.service;

import com.balance.dao.BalanceDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.balance.entity.Balance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class BalanceService {

    @Resource
    BalanceDao balanceDao;

    /**
     * 扣减余额
     */
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String userId, BigDecimal count) {
        // 根据用户id  查询出用户余额
        Balance balance = getByUserId(userId);

        // 判断是否可以扣减
        if (null != balance && balance.getCount().subtract(count).intValue() < 0) {
            throw new RuntimeException("余额不足");
        }

        // 执行扣减余额操作
        balance.setCount(balance.getCount().subtract(count));
        balanceDao.updateById(balance);

    }

    /**
     * 根据用户id查询用户余额
     * @param userId
     * @return
     */
    public Balance getByUserId(String userId) {
        // 根据用户id  查询出用户余额
        QueryWrapper<Balance> wrapper = new QueryWrapper<>();
        Balance balance = new Balance();
        balance.setUserId(userId);
        wrapper.setEntity(balance);

        balance = balanceDao.selectOne(wrapper);
        return balance;
    }

    public void insert(String userId, BigDecimal count) {
        // 根据用户id  查询出用户余额
        QueryWrapper<Balance> wrapper = new QueryWrapper<>();
        Balance balance = new Balance();
        balance.setUserId(userId);
        wrapper.setEntity(balance);

        balance = balanceDao.selectOne(wrapper);


        if (null != balance) {
            balance.setCount(balance.getCount().add(count));
            QueryWrapper<Balance> updateWrapper = new QueryWrapper<>();
            Balance updateBalance = new Balance();
            updateBalance.setFdId(balance.getFdId());
            updateWrapper.setEntity(updateBalance);
            balanceDao.update(balance, updateWrapper);
        } else {
            balance = new Balance();
            balance.setUserId(userId);
            balance.setCount(count);
            balance.setFdId(IdWorker.get32UUID());
            balanceDao.insert(balance);
        }
    }
}

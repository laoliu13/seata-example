package com.seata.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.seata.example.dao.BalanceDao;
import com.seata.example.entity.Balance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Transactional(rollbackFor = Exception.class)
public class BalanceService {

    @Resource
    BalanceDao balanceDao;

    /**
     * 扣减余额
     */
    public void deduct(String userId, BigDecimal count) {
        // 根据用户id  查询出用户余额
        QueryWrapper<Balance> wrapper = new QueryWrapper<>();
        Balance balance = new Balance();
        balance.setUserId(userId);
        wrapper.setEntity(balance);

        balance = balanceDao.selectOne(wrapper);

        // 判断是否可以扣减
        if (null != balance && balance.getCount().subtract(count).intValue() < 0) {
            throw new RuntimeException("余额不足");
        }

        // 执行扣减余额操作
        balance.setCount(balance.getCount().subtract(count));
        balanceDao.updateById(balance);

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

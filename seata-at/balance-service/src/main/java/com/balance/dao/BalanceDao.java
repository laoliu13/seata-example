package com.balance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.balance.entity.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BalanceDao extends BaseMapper<Balance> {
}

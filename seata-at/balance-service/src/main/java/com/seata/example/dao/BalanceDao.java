package com.seata.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seata.example.entity.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BalanceDao extends BaseMapper<Balance> {
}

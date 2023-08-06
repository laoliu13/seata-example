# seata-at
Seata AT模式 demo
- 参考官网的demo，采用springcloud + nacos + feign作为代码框架
- 这里有balance账户余额模块和order订单模块，模拟余额支付订单时的一些场景，如余额足够支付时，余额不足支付时回滚的操作
- 注意的几点
    - nacos配置中心需要增加seata 服务分组，要与服务端nacos-config.txt中service.vgroup_mapping的后缀对应
    - 需要在调用方实现方法上增加@GlobalTransactional

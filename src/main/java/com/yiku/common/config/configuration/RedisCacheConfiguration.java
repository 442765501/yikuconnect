//package com.yiku.common.config.configuration;
//
//import com.alibaba.fastjson.JSON;
//import com.yiku.common.util.LogUtil;
//import com.yiku.common.util.StringUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//
///**
// * Project Name: kc-financialservice
// * Package Name: cn.kingcar.financialservice.biz.common.config.configuration
// * Function: Redis
// * user: San
// * Date:2017/9/4
// */
//
//@Configuration
//@EnableCaching
//public class RedisCacheConfiguration extends CachingConfigurerSupport {
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Value("${redis.pool.maxIdle}")
//    private int maxIdle;
//
//    @Value("${redis.pool.maxTotal}")
//    private int maxTotal;
//
//    @Value("${redis.pool.maxWaitMillis}")
//    private int maxWaitMillis;
//
//    @Value("${redis.pool.testOnBorrow}")
//    private boolean testOnBorrow;
//
//    @Value("${redis.pool.testWhileIdle}")
//    private boolean testWhileIdle;
//
//    @Value("${redis.pool.testOnReturn}")
//    private boolean testOnReturn;
//
//    @Bean("jedisPool")
//    public JedisPool redisPoolFactory() {
//        LogUtil.info("JedisPool注入成功！！", "redis地址：" + host + ":" + port);
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(10000);
//        jedisPoolConfig.setTestOnBorrow(true);
//        JedisPool jedisPool;
//        if (StringUtil.isBlank(password)) {
//            jedisPool = new JedisPool(jedisPoolConfig, host, port, 100000, null);
//        } else {
//            jedisPool = new JedisPool(jedisPoolConfig, host, port, 100000, password);
//        }
//        LogUtil.info("连接池：", "{}", JSON.toJSONString(jedisPool.getNumActive()));
//
//        return jedisPool;
//    }
//
//
//}

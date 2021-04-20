package com;

import com.yiku.common.util.LogUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.yiku", exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
@EnableTransactionManagement
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        if (context.isActive()) {
            LogUtil.info("yiku", "启动完成");
        }
    }
//
//    @Resource
//    private RedisTemplate redisTemplate;
//
//    @Bean
//    public RedisTemplate redisTemplateInit() {
//        //设置序列化Key的实例化对象
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        //设置序列化Value的实例化对象
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return redisTemplate;
//    }
}

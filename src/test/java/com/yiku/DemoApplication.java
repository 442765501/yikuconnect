package com.yiku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Project Name: kc-apiservice
 * Package Name: cn.kingcar.apiservice.provider
 * Function: API服务中心
 * user: San
 * Date:2018/4/8
 */

@SpringBootApplication(scanBasePackages = "com.yiku")
@ServletComponentScan
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
    }
}

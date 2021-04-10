package com.yiku.common.config.configuration;

import com.yiku.common.config.properties.DingdingProperties;
import com.yiku.common.config.properties.TsumProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/27 16:05
 */
@Configuration
public class SysConfiguration {

    @Bean
    public TsumProperties getTsumProperties() {
        return new TsumProperties();
    }

    @Bean
    public DingdingProperties getDingdingServiceProperties() {
        return new DingdingProperties();
    }
}

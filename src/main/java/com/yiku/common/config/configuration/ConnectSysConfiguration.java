package com.yiku.common.config.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 *
 * @Description:
 * @Author:
 */

@Configuration
@MapperScan(basePackages = "com.yiku.dao.entity",sqlSessionFactoryRef = "sqlSessionFactory")
public class ConnectSysConfiguration {


    @Bean("datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource datasource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
    @Bean(value = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("datasource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/yiku/dao/mapper/*/*.xml"));
        return sqlSessionFactory.getObject();
    }
}

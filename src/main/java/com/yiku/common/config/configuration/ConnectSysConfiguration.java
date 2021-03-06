package com.yiku.common.config.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@MapperScan(basePackages = "com.yiku.dao.entity", sqlSessionFactoryRef = "tsumSqlSessionFactory")
public class ConnectSysConfiguration {


    @Value("${spring.datasource.tsum.driver-class-name}")
    String driverClass;
    @Value("${spring.datasource.tsum.url}")
    String url;
    @Value("${spring.datasource.tsum.username}")
    String userName;
    @Value("${spring.datasource.tsum.password}")
    String passWord;


    @Bean("tsumDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.tsum")
    public DataSource datasource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(passWord);
        return druidDataSource;
    }
    @Primary
    @Bean(value = "tsumSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("tsumDatasource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/yiku/dao/tsumMapper/*/*.xml"));
        return sqlSessionFactory.getObject();
    }

}

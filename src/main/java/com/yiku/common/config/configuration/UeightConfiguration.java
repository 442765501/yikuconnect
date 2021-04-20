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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/19 15:37
 */
@Configuration
@MapperScan(basePackages = "com.yiku.dao.entity",sqlSessionFactoryRef = "ueightSqlSessionFactory")
public class UeightConfiguration {


    @Value("${spring.datasource.ueight.driver-class-name}")
    String driverClass;
    @Value("${spring.datasource.ueight.url}")
    String url;
    @Value("${spring.datasource.ueight.username}")
    String userName;
    @Value("${spring.datasource.ueight.password}")
    String passWord;

    @Bean("ueightDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.ueight")
    public DataSource datasource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(passWord);
        return druidDataSource;
    }
    @Bean(value = "ueightSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ueightDatasource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/yiku/dao/ueightMapper/ueight/*.xml"));
        return sqlSessionFactory.getObject();
    }
}

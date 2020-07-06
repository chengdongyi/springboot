package com.example.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.dao.lvip", sqlSessionTemplateRef = "lvipSqlSessionTemplate")
public class LvipDataSourceConfig {

    @Bean("lvipDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.lvip")
    public DataSource lvipDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("lvipSqlSessionFactory")
    public SqlSessionFactory lvipSqlSessionFactory(@Qualifier("lvipDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("lvipTransactionManager")
    public DataSourceTransactionManager lvipTransactionManager(@Qualifier("lvipDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("lvipSqlSessionTemplate")
    public SqlSessionTemplate lvipSqlSessionTemplate(@Qualifier("lvipSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

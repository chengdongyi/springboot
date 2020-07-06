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
@MapperScan(basePackages = "com.example.dao.jobs", sqlSessionTemplateRef = "jobsSqlSessionTemplate")
public class JobsDataSourceConfig {

    @Bean("jobsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.jobs")
    public DataSource jobsDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean("jobsSqlSessionFactory")
    public SqlSessionFactory jobsSqlSessionFactory(@Qualifier("jobsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("jobsTransactionManager")
    public DataSourceTransactionManager jobsTransactionManager(@Qualifier("jobsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("jobsSqlSessionTemplate")
    public SqlSessionTemplate jobsSqlSessionTemplate(@Qualifier("jobsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
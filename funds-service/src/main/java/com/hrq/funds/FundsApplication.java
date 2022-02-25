package com.hrq.funds;

import cn.itcast.fegin.clients.UserClient;
import io.seata.rm.datasource.DataSourceProxy;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@MapperScan("com.hrq.funds.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {UserClient.class})//注入UserClient客户端
public class FundsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundsApplication.class, args);
    }

    /**
     * 创建restTemplate并注入spring容器
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
/*
    *//**
     * 全局定义负载均衡策略
     * @return
     *//*
    @Bean
    public IRule randomRule() {
        return new RandomRule();
    }*/
    /*@Autowired
    private DataSource dataSource;
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProx());
        return sqlSessionFactoryBean;
    }
    @Bean
    @ConditionalOnBean(DataSource.class)
    public DataSourceProxy dataSourceProx() {
        return new DataSourceProxy(dataSource);
    }*/

}
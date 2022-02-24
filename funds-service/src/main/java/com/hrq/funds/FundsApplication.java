package com.hrq.funds;

import cn.itcast.fegin.clients.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
}
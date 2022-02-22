package cn.itcast.fegin.config;

import cn.itcast.fegin.clients.fallback.UserClientFallBackFactory;
import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
    @Bean
    public UserClientFallBackFactory userClientFallBackFactory(){
        return new UserClientFallBackFactory();
    }
}

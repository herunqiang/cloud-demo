package cn.itcast.fegin.clients;

import cn.itcast.fegin.clients.fallback.UserBalClientFallBackFactory;
import cn.itcast.fegin.clients.fallback.UserClientFallBackFactory;
import cn.itcast.fegin.config.DefaultFeignConfiguration;
import cn.itcast.fegin.pojo.UserBal;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "funds-service",
        configuration = DefaultFeignConfiguration.class,
        fallbackFactory = UserBalClientFallBackFactory.class)
public interface UserBalClient {
    @PutMapping("/userBal/updateBalByUserId")
    public Integer updateBalByUserId(@Param("userBal") UserBal userBal);
}

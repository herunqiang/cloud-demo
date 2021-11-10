package cn.itcast.fegin.clients;

import cn.itcast.fegin.config.DefaultFeignConfiguration;
import cn.itcast.fegin.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userservice",configuration = DefaultFeignConfiguration.class)
public interface UserClient {
    @GetMapping("/user/{id}")
    User findUserById(@PathVariable("id") Long userId);
}

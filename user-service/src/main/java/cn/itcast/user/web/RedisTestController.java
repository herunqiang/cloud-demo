package cn.itcast.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 测试分布式redis
 */
@RestController
@RequestMapping("redis")
public class RedisTestController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @PostMapping("/set/{key}/{value}")
    public String setKey(@PathVariable("key") String key , @PathVariable("value") String value){
        redisTemplate.opsForValue().set(key,value);
        return "set成功";
    }
    @GetMapping("/get/{key}")
    public String getValue(@PathVariable("key") String key ){
        String value = redisTemplate.opsForValue().get(key);
        return value;
    }
}

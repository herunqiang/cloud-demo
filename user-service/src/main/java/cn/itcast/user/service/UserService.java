package cn.itcast.user.service;

import cn.itcast.user.mapper.UserMapper;
import cn.itcast.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) throws InterruptedException {
        if (id == 1) {//测试慢调用熔断
            Thread.sleep(60);
        } else if (id == 2) {//异常熔断
            throw new RuntimeException("测试异常熔断");
        }
        return userMapper.findById(id);
    }
}
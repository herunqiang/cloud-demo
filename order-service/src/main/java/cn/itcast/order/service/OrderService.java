package cn.itcast.order.service;

import cn.itcast.fegin.clients.UserClient;
import cn.itcast.fegin.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.根据用户id查询用户信息
        //用服务名替换ip
        //User userInfo = restTemplate.getForObject("http://userservice/user/" + order.getUserId(), User.class);
        User userInfo = userClient.findUserById(order.getUserId());
        order.setUser(userInfo);
        // 4.返回
        return order;
    }
    @SentinelResource("goods")
    public String queryOrder() {
        return "查询成功";
    }
}

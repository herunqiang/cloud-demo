package cn.itcast.order.service.imp;

import cn.itcast.fegin.clients.UserBalClient;
import cn.itcast.fegin.clients.UserClient;
import cn.itcast.fegin.pojo.User;
import cn.itcast.fegin.pojo.UserBal;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;
    @Autowired
    private UserBalClient userBalClient;
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

    /**
     * 保存订单   分布式事务test
     * @param order
     * @return
     */
    @Override
    @GlobalTransactional
    public String saveOrder(Order order) {
        String xid = RootContext.getXID();
        System.out.println("订单事务id："+xid);

        //2.扣余额
        try{
            UserBal userBal = new UserBal();
            userBal.setUserId(order.getUser().getId());
            userBal.setBalance(new Double((-1)*(order.getPrice().longValue())));
            Integer integer = userBalClient.updateBalByUserId(userBal);
            if (integer !=1){
                throw new RuntimeException("余额更新失败行数不为1");
            }
        }catch (Exception e ){
            e.printStackTrace();
            throw new RuntimeException("扣余额失败：" + e.getMessage());
        }
        //下单
        save(order);
        return "下单成功";
    }
    @Transactional
    public void save(Order order) {
        //1.保存订单
        int orderRows =  orderMapper.saveOrder(order);
    }
}

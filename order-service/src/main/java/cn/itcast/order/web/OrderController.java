package cn.itcast.order.web;

import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;
    @SentinelResource("hot")//支持热点限流配置，不加这个注解热点限流规则无效
    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId, @RequestHeader(value = "words",required = false) String words) {
        System.out.println(words);
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }
    @GetMapping("/query")
    public String queryOrder() {
        //查询订单
        this.orderService.queryOrder();
        return "查询订单成功";
    }
    @GetMapping("/update")
    public String updateOrder() {

        return "更新订单成功";
    }
    @GetMapping("/save")
    public String saveOrder() {
        this.orderService.queryOrder();
        return "保存订单成功";
    }
}

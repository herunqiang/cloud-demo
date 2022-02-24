package cn.itcast.fegin.clients.fallback;

import cn.itcast.fegin.clients.UserBalClient;
import cn.itcast.fegin.clients.UserClient;
import cn.itcast.fegin.pojo.UserBal;
import feign.Param;
import feign.hystrix.FallbackFactory;


public class UserBalClientFallBackFactory implements FallbackFactory<UserBalClient> {
    @Override
    public UserBalClient create(Throwable throwable) {
        return new UserBalClient() {
            @Override
            public Integer updateBalByUserId(@Param("userBal") UserBal userBal) {
                throwable.printStackTrace();
                System.err.println("更新余额异常");
                return 0;
            }
        };
    }
}

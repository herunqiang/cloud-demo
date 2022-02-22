package cn.itcast.fegin.clients.fallback;

import cn.itcast.fegin.clients.UserClient;
import cn.itcast.fegin.pojo.User;
import feign.hystrix.FallbackFactory;


public class UserClientFallBackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public User findUserById(Long userId) {
                throwable.printStackTrace();
                System.err.println("查询用户异常");
                return new User();
            }
        };
    }
}

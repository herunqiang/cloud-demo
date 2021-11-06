package cn.itcast.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
@Component
//@Order(1)//数值与优先级成反比
public class AuthorizationFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取请求参数authorization，如果为admin，则继续，否则终止
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        String authorization = queryParams.getFirst("authorization");
        if ("admin".equals(authorization)) { //继续执行其他过滤器
            return chain.filter(exchange);
        }else{//直接响应
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return  1;
    }
}

package cn.itcast.order.sentinel;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class HeaderOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String origin = request.getHeader("origin");//网关中会定义这个origin，然后改变量的值会用来配置授权规则
        if (StringUtils.isBlank(origin)) {
            return "blank";
        }
        return origin;
    }
}

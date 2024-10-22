package icu.yunke.gateway.filter;


import icu.yunke.framework.common.constants.UserConstant;
import icu.yunke.framework.common.exception.BaseException;
import icu.yunke.framework.common.exception.base.JwtError;
import icu.yunke.framework.common.model.dto.UserDTO;
import icu.yunke.framework.common.util.JwtUtils;
import icu.yunke.gateway.util.FilterUtil;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * token过滤器，过滤请求是否存在token
 */
@Component
@AllArgsConstructor
public class TokenGlobalFilter implements GlobalFilter, Ordered {

    private final RedissonClient redissonClient; ;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        //获取token
        String token = Optional.ofNullable(request.getHeaders().get("Authorization"))
                .filter(tokenList -> !tokenList.isEmpty())
                .map(tokens -> tokens.get(0))
                .orElse(null);
        //如果token为空
        if (token == null) {
            //  TODO 通过由微服务自己判断是否通过
            return chain.filter(exchange);
        } else {
            // 去除开头的"Bearer "
            token = token.replace("Bearer ", "");
        }

       //将token转成id并获取对象
        Long userId = null;
        try {
            userId = JwtUtils.getUserid(token);
        } catch (BaseException e) {
            // 解析失败 TODO 说明token出现问题，直接返回异常，结束
            return chain.filter(exchange);
        }

        // 设置userid到请求头
        request = exchange.getRequest().mutate().header(UserConstant.USER_ID, String.valueOf(userId)).build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        // 第二个过滤
        return 1;
    }
}

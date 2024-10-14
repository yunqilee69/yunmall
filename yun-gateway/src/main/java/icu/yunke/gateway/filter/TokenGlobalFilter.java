package icu.yunke.gateway.filter;


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
            //return FilterUtil.failure(exchange.getResponse(), "token为空");
        } else {
            // 去除开头的"Bearer "
            token = token.replace("Bearer ", "");
        }

        try {
            // TODO 判断userid是否在redis中
           //将token转成id并获取对象
            Long userid = JwtUtils.getUserid(token);
            UserDTO userDTO = (UserDTO) redissonClient.getBucket(userid.toString()).get();
            //将userDTO放入exchange中
            //exchange.getAttributes().put("userDTO", userDTO);
            //如果不能成功获取对象则说明该token非法
            if (null == userDTO) {
                return FilterUtil.failure(exchange.getResponse(), JwtError.TOKEN_IS_ILLEGAL.getMessage());
            }
            // TODO 获取到了token及用户信息，进行流量染色，设置userid等信息到请求头
        } catch (Exception e) {
            return FilterUtil.failure(exchange.getResponse(), JwtError.TOKEN_IS_ILLEGAL.getMessage());
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 第二个过滤
        return 1;
    }
}

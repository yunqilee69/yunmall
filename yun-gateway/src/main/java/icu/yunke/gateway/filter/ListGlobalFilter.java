package icu.yunke.gateway.filter;

import icu.yunke.gateway.util.FilterUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 黑白名单过滤器
 */
@Component
public class ListGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取路由
        String path = exchange.getRequest().getURI().getPath();
        //获取ip
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        // TODO 实现黑名单
        //如果ip属于黑名单则拦截
        if(FilterUtil.isBlackList(ip)){
            return FilterUtil.failure(exchange.getResponse(),"你是黑名单");
        }

        // TODO 实现白名单，从nacos获取白名单配置进行校验
        // 根据路由判断
        if (FilterUtil.isWhiteList(path)) {
            // TODO 进行流量染色，目前不需要

        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 第一个过滤
        return 0;
    }
}

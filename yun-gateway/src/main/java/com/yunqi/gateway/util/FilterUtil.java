package com.yunqi.gateway.util;

import com.alibaba.fastjson2.JSON;
import com.yunqi.common.web.entity.ApiResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class FilterUtil {

    /**
     * 判断是否为黑名单
     * @param ip
     * @return
     */
    public static boolean isBlackList(String ip) {
        return false;
    }

    /**
     * 判断是否为白名单
     * @param path
     * @return
     */
    public static boolean isWhiteList(String path) {
        return true;
    }

    /**
     * 返回失败结果
     * @param response
     * @param msg
     * @return
     */
    public static Mono<Void> failure(ServerHttpResponse response, String msg) {
        ApiResponse<Object> result = ApiResponse.failure(msg);
        byte[] bits = JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

}

package com.den.we.config;

import com.den.we.entity.User;
import com.den.we.transform.AuthidUserInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

/**
 * 全局身份认证
 * @author fatKarin
 * @date 2019/9/30 11:08
 */
@Configuration
@ConfigurationProperties("filter.invalid")
public class GlobalAuthConfig implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate redisTemplate;

    @Getter
    @Setter
    private String[] unlimitedPath;

    // session 用户信息属性
    private static final String SESSION_USER_INFO = "sessionAttr:USER_INFO";
    // redis key session 储存前缀
    private static final String SESSION_HASH_KEY_PREFIX = "spring:session:sessions:";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 是否是开放接口
        if(Arrays.asList(unlimitedPath).contains(exchange.getRequest().getURI().getPath())){
            return chain.filter(exchange);
        }
        //获取请求头
        String token = exchange.getRequest().getHeaders().getFirst("x-auth-token");
        ServerHttpResponse response = exchange.getResponse();
        if (token == null) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //查询是否已认证
        AuthidUserInfo user = (AuthidUserInfo) redisTemplate.opsForHash().get(SESSION_HASH_KEY_PREFIX + token,SESSION_USER_INFO);

        if (user == null) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

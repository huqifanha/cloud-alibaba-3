package com.test.order.filter;

import com.test.order.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthTokenGlobalFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 获取token
        String jwtToken = request.getHeaders().getFirst("Authorization");

        // 非白名单路径并没有token， 401 Unauthorized
        if (!StringUtils.hasText(jwtToken) && !isWhiteListed(path)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String uid = null;
        try {
            Claims claims = JwtUtil.parseJWT(jwtToken);
            uid = claims.getSubject();
        } catch (Exception ignored) {
            if (!isWhiteListed(path)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        // 创建新的请求，将 uid 放入 header
        ServerHttpRequest.Builder mutate = request.mutate();
        if (StringUtils.hasText(uid)) {
            mutate.header("uid", uid);
        }

        // 将修改后的请求继续传递
        ServerHttpRequest modifiedRequest = mutate
                .build();
        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(modifiedRequest)
                .build();
        return chain.filter(modifiedExchange);
    }

    // 白名单路径（不强制登录），搞一个后台配置，然后再放到redis中
    private static final List<String> WHITE_LIST = Arrays.asList(
            "/user/**",
            "/order/**",
            "/management/**"
    );

    // 判断路径是否在白名单中
    private boolean isWhiteListed(String path) {
        return WHITE_LIST.stream().anyMatch(whiteListedPath -> path.matches(whiteListedPath.replace("**", ".*")));
    }

}

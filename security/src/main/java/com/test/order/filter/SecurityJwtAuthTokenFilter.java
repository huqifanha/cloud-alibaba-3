package com.test.order.filter;


import com.test.order.pojo.LoginUser;
import com.test.order.utils.JwtUtil;
import com.test.order.utils.redis.RedisCache;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityJwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");

        // token 不存在直接放行
        if (!StringUtils.hasText(jwtToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(jwtToken);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token invalid");
        }

        // 获取登录信息
        LoginUser loginUser = redisCache.getCacheObject(userId);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("token invalid");
        }

        //存入SecurityContextHolder
        //获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }


}

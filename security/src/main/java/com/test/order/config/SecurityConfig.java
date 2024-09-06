package com.test.order.config;

import com.test.order.filter.SecurityJwtAuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity    // 添加 security 过滤器
@EnableMethodSecurity //开启权限配置
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private SecurityJwtAuthTokenFilter securityJwtAuthTokenFilter;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 密码明文加密配置
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 基于token，不需要 csrf
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                // 基于token，不需要 session
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 配置校验器
                .addFilterBefore(securityJwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 异常处理，通过全局异常返回
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint)
                )
                //认证成功处理器
                // 设置权限
                .authorizeHttpRequests(authorize -> authorize
                                // 除了后台登录接口开启匿名访问，其他后台接口均需要授权，后台管理路由要以/management开始或自定义后再次放开
                                .requestMatchers("/management/login").anonymous()
                                .requestMatchers("/management/**").authenticated()
                                .anyRequest().permitAll()
                        // 这里也可以设置接口权限
//                        .requestMatchers("/management/**").hasAuthority("menu:main")
                )
                .build();
    }

}

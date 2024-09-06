package com.test.order.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

/**
 * @Author hqf
 * @Date 2024/8/31
 */
@Configuration
public class ApplicationConfig {


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder
                .setConnectTimeout(Duration.ofSeconds(60))
                .build();
        return restTemplate;
    }

}

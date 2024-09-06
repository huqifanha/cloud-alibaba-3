package com.test.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
@RefreshScope // 使用@Value获取nacos配置加上此注解才能动态刷新
public class OrderController {


    @Value("${user.name}")
    String userName;

    @RequestMapping("/add")
    public String addOrder(){
        return userName;
    }

}

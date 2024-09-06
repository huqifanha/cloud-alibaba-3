package com.test.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "order-service", path = "/order")
public interface OrderFeign {

    @RequestMapping("/add")
    String addOrder();

}

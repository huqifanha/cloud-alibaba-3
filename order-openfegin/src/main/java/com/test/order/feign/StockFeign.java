package com.test.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "stock-service", path = "/stock")
public interface StockFeign {

    @RequestMapping("/reduct")
    String reduct();

    @RequestMapping("/product/{id}")
    String product(@PathVariable("id") String id);

}

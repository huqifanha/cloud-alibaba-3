package com.test.order.stockfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "stock-service", path = "/stock")
public interface StockFeign {

    @RequestMapping("/reduct")
    public String reduct();

}

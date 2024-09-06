package com.test.order.controller;


import com.test.order.feign.StockFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    StockFeign stockFeign;

    @RequestMapping("/add")
    public String addOrder() {
        System.out.println("下单成功！");
//        String forObject = restTemplate.getForObject("http://stock-service/stock/reduct", String.class);
        String reduct = stockFeign.reduct();
        return reduct;
    }

    @RequestMapping("/product/{id}")
    public String product(@PathVariable String id) {
        System.out.println("下单" + id + "成功！");
//        String forObject = restTemplate.getForObject("http://stock-service/stock/reduct", String.class);
        String reduct = stockFeign.product(id);
        return reduct;
    }

}

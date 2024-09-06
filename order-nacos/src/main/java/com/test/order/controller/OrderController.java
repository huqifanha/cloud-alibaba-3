package com.test.order.controller;


import com.test.order.stockfeign.StockFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    StockFeign stockFeign;

    @RequestMapping("/add")
    public String addOrder(){
        System.out.println("下单成功！");
        String forObject = stockFeign.reduct();
        return forObject;
    }

}

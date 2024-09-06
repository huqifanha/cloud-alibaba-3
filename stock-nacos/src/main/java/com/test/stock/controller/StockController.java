package com.test.stock.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/reduct")
    public String reduct(){
        System.out.println("扣减库存成功！");
        return "扣减库存成功" + port;
    }

    @RequestMapping("/product/{id}")
    public String product(@PathVariable("id") String id){
        System.out.println("查询商品成功：" + id);
        return "扣减库存成功" + port + "------" + id;
    }

}

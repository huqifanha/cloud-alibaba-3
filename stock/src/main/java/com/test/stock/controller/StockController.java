package com.test.stock.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @RequestMapping("/reduct")
    public String addOrder(){
        System.out.println("扣减库存成功！");
        return "扣减库存成功";
    }

}

package com.test.order.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ConsumerController {

    @RequestMapping("/send")
    public String sendMsg() {

        return "success";
    }

}

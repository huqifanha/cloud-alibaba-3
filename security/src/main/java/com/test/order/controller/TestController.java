package com.test.order.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/test")
    public String login(@RequestHeader("uid") String uid) {
        System.out.println(uid);
        return "management";
    }


}

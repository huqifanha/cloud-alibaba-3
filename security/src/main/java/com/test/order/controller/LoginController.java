package com.test.order.controller;


import com.test.order.pojo.User;
import com.test.order.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * 管理后台 - 登录
     */
    @PostMapping("/management/login")
    public HashMap<String, String> login(@RequestBody User user) {
        return loginService.loginManagement(user);
    }


    /**
     * 管理后台 - 注销
     */
    @PostMapping("/management/logout")
    public String logout() {
        return loginService.logoutManagement();
    }


    /**
     * 邮箱登录
     */
    @PostMapping("/user/login")
    public HashMap<String, String> loginByEmail(@RequestBody User user) {
        return loginService.login(user);
    }


    /**
     * 退出登录
     */
    @PostMapping("/user/logout")
    public String logout(@RequestHeader("Authorization") String accessToken) {
        return loginService.logout(accessToken);
    }


    /**
     * 注册
     */
    @PostMapping("/user/resister")
    public String resister(@RequestBody User user) {
        return loginService.resister(user);
    }


}

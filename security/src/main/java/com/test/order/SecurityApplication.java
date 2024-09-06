package com.test.order;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.test.order.mapper") // 扫Mapper包
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class);
    }


}

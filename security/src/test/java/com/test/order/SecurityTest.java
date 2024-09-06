package com.test.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class SecurityTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void commonTest() {
        String encode = passwordEncoder.encode("111111");
        System.out.println(encode);
    }

}

package com.test.order.utils;

import java.util.UUID;


public class UUIDUtil {

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    public static String getUUIDNotReplace() {
        String token = UUID.randomUUID().toString();
        return token;
    }

}

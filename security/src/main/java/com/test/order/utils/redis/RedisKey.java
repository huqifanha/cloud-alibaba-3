package com.test.order.utils.redis;

public enum RedisKey {

    LOGIN_USER("loginUser:"),   // 登录用户 + userId
    ACCESS_TOKEN_USERID("accessTokenUserid:"),   // AccessToken获取userid + AccessToken

    ;

    private final String keyPre;

    RedisKey(String keyPre) {
        this.keyPre = keyPre;
    }

    public String combKey(String value) {
        return this.keyPre + value;
    }

    public String getKeyPre() {
        return keyPre;
    }
}

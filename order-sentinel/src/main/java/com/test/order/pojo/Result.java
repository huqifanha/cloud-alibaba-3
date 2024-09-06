package com.test.order.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: hqf
 * @Date: 2023/4/20 15:12
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg);
    }

}

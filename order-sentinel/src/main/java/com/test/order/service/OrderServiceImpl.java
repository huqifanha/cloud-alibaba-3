package com.test.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @Author: hqf
 * @Date: 2023/4/20 15:59
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    @SentinelResource(value = "getUser", blockHandler = "blockHandlerForgetUser")
    public String getUser() {
        return "查询用户";
    }

    public String blockHandlerForgetUser(BlockException e) {
        return "链路流控用户!";
    }

}

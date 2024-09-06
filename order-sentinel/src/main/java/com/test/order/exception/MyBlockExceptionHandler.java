package com.test.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.order.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


/**
 * @Author: hqf
 * @Date: 2023/4/20 15:11
 * sentinel 全局异常处理 使用了@SentinelResource注解不会再次生效
 */
@Component // 注册层spring组件
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        logger.info("BlockException :" + e.getRule());

        Result result = null;

        if (e instanceof FlowException) {
            result = Result.error(10001, "接口限流！");
        } else if (e instanceof DegradeException) {
            result = Result.error(10002, "服务降级！");
        } else if (e instanceof ParamFlowException) {
            result = Result.error(10003, "热点参数限流！");
        } else if (e instanceof SystemBlockException) {
            result = Result.error(10004, "触发系统保护规则！");
        } else if (e instanceof AuthorityException) {
            result = Result.error(10005, "授权规则未通过！");
        }

        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), result);
    }

}

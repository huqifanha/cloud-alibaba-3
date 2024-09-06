package com.test.order.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.test.order.service.OrderService;
import com.test.order.stockfeign.StockFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    StockFeign stockFeign;


    /**
     * 并发数 QPS，每秒请求
     * @return
     */
    @RequestMapping("/flow")
//    @SentinelResource(value = "flow", blockHandler = "flowBlockHandler")
    public String flow() {
        return "正常访问";
    }

    public String flowBlockHandler(BlockException e) {
        System.out.println(e.getMessage());
        return "被流控";
    }

    /**
     * 并发线程数
     */
    @RequestMapping("/flow/thread")
    @SentinelResource(value = "flowThread", blockHandler = "flowBlockHandler")
    public String flowThread() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        return "正常访问";
    }


    /**
     * 流控模式 ： 关联测试
     * 关联资源：/order/add
     * 当 /order/add 达到流控限制则 /order/get 被流控
     */
    @RequestMapping("/get")
    public String get() {
        return "关联测试get";
    }

    /**
     * 流控模式 ： 关联测试
     */
    @RequestMapping("/add")
    public String add() {
        return "关联测试add";
    }


    @Autowired
    OrderService orderService;

    /**
     * 流控模式 ： 链路测试
     * 关联资源：getUser
     * 只针对 test2 流控， /test1 可以无限制访问
     */
    @RequestMapping("/test1")
    public String test1() {
        String user = orderService.getUser();
        return "链路test1:" + user;
    }

    /**
     * 流控模式 ： 链路测试
     * 关联资源：getUser
     * 针对 test2 流控， /test2 会被流控
     */
    @RequestMapping("/test2")
    public String test2() {
        String user = orderService.getUser();
        return "链路test2:" + user;
    }


    /**
     * 流控效果 ： warm up
     * 针对激增流量，一段时间无人访问，突然洪峰流量大量涌入，然后又很长时间处于平稳
     * 预热/冷启动 ，流量突然增加直接把系统压垮，通过冷启动让流量缓慢增加在一定时间内增加到阈值上限
     * 例如：缓存预热 连接池，
     */
    @RequestMapping("/warmup")
    public String warmup() {
        return "warm up";
    }


    /**
     * 流控效果 ： 排队等待
     * 针对脉冲流量，隔一段时间空闲突然洪峰进来然后又空闲又洪峰，重复此动作，
     * 通过排队等待方式在空闲时间处理剩余流量
     */
    @RequestMapping("/queue")
    public String queue() {
        return "queue";
    }


    /**
     * 熔断降级: 慢调用比例
     */
    @RequestMapping("/ratio")
    public String ratio() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return "熔断降级-慢调用比例";
    }

    /**
     * 熔断降级: 异常比例
     */
    @RequestMapping("/exception")
    public String exception() {
        int i = 1 / 0;
        return "熔断降级-异常比例";
    }

    /**
     * 熔断降级: 异常数量
     */
    @RequestMapping("/error")
    public String error() {
        int i = 1 / 0;
        return "熔断降级-异常数量";
    }


    /**
     * 热点参数流控
     * 热点商品访问/操作控制 ， 用户/ip防刷
     * 必须结合 SentinelResource 使用
     */
    @RequestMapping("/param/{id}")
    @SentinelResource(value = "paramById", blockHandler = "blockHandlerForParamById")
    public String paramById(@PathVariable("id") Integer id) {
        return "正常热点参数";
    }

    public static String blockHandlerForParamById(@PathVariable("id") Integer id, BlockException e) {
        return "热点参数流控：" + id;
    }

}

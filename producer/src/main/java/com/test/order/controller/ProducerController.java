package com.test.order.controller;


import com.test.order.feign.OrderFeign;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private RocketMQTemplate rocketmqTemplate;
    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("/send")
    public String sendMsg() {
        String s = orderFeign.addOrder();

//        String topic = "order-producer";
        String topic = "order_producer";
        Message<String> msg = MessageBuilder.withPayload("Hello RocketMQ! " + s)
                .build();

        try {
            SendResult sendResult = rocketmqTemplate.syncSend(topic, msg);
            System.out.println("发送消息成功：" + sendResult.getSendStatus());
        } catch (Exception e) {
            System.err.println("发送消息失败：" + e.getMessage());
        }
//        rocketmqTemplate.asyncSend(topic, msg, new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                System.out.println("发送消息成功：" + sendResult.getSendStatus());
//            }
//            @Override
//            public void onException(Throwable throwable) {
//                System.out.println("发送消息失败：" + throwable.getMessage());
//            }
//        });
        return "success";
    }

}

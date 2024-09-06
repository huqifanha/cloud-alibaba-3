package com.test.order.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "order_producer", consumerGroup = "producer_group")
public class RocketMQConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("消费消息-" + message);
    }

}

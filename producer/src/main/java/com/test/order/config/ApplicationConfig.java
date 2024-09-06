package com.test.order.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Configuration
public class ApplicationConfig {

    @Value("${rocketmq.name-server}")
    String nameServer;

    @Value("${rocketmq.producer.group}")
    String producerGroup;

    @Value("${rocketmq.producer.sendMessageTimeout}")
    int sendMsgTimeout;

    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    int retryTimesWhenSendFailed;

    @Value("${rocketmq.producer.maxMessageSize}")
    int maxMessageSize;

    @Value("${rocketmq.producer.retryTimesWhenSendAsyncFailed}")
    int retryTimesWhenSendAsyncFailed;

    @Bean
    public DefaultMQProducer defaultMqProducer() {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr(nameServer);
        producer.setProducerGroup(producerGroup);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendAsyncFailed);
        return producer;
    }

    @Bean
    public RocketMQTemplate rocketMqTemplate(DefaultMQProducer producer){
        RocketMQTemplate rocketMqTemplate = new RocketMQTemplate();
        rocketMqTemplate.setProducer(producer);
        return rocketMqTemplate;
    }

}

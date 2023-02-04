package com.ylb.rocketmq.controller;

import com.ylb.rocketmq.basic.SpringProduce;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/MQTest")
public class MqController {
    //消息发送的topic
    private final String topic = "TestTopic";

    @Resource
    private SpringProduce producer;

    /**
     * 发送一个普通消息
     * @param message
     * @return
     */
    @RequestMapping("/sendMessage")
    public String sendMessage(String message){
        producer.sendMessage(topic,message);
        return "消息发送完成";
    }

    /**
     * 发送一个事务消息
     * @param message
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/sendTransactionMessage")
    public String sendTransactionMessage(String message) throws InterruptedException {
        producer.sendMessageInTransaction(topic,message);
        return "消息发送完成";
    }
}

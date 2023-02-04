package com.ylb.rocketmq.basic;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对产品的bean RocketMQTemplate 进行包装，与redis类似，中间件的套路
 * 目的：中间件提供的是产品自身最通用的最单一职能的工具，而我们自己对其进行适当的包装扩展后再使用。
 */
@Component
public class SpringProduce {
    /**
     * 注入产品的bean，主要添加了依赖包就能导入
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送普通消息：不添加其他逻辑，裸调
     * @param topic
     * @param msg
     */
    public void sendMessage(String topic,String msg){
        rocketMQTemplate.convertAndSend(topic,msg);
    }

    /**
     * 发送同步消息
     * @param topic
     * @param msg
     */
    public SendResult syncSend(String topic,String msg){
        SendResult sendResult = rocketMQTemplate.syncSend(topic, msg);
        return sendResult;
    }

    /**
     * 有序的发送同步消息
     * 在消费的时候也必须有序消费：在监听中配consumeMode = ConsumeMode.ORDERLY,consumeThreadMax = 1
     * @param topic
     * @param msg
     * @return
     */
    public SendResult syncSendOrderly(String topic,String msg){
        //一个Topic下可能会有多个消息队列queue，在发送消息时可以指定要发送到哪个消息队列中
        rocketMQTemplate.setMessageQueueSelector(new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, org.apache.rocketmq.common.message.Message message, Object o) {
                return list.get(0);
            }
        });
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(topic, msg, "");
        return sendResult;
    }

    /**
     * 发送异步消息
     * @param topic
     * @param msg
     */
    public void asyncSend(String topic,String msg){
        rocketMQTemplate.asyncSend(topic,msg,new SendCallback(){
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("send success:"+sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("send fail:"+throwable.getMessage());
            }
        });
    }

    /**
     * 发送有序的异步消息
     * @param topic
     * @param msg
     */
    public void asyncSendOrderly(String topic,String msg){
        //一个Topic下可能会有多个消息队列queue，在发送消息时可以指定要发送到哪个消息队列中
        rocketMQTemplate.setMessageQueueSelector(new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, org.apache.rocketmq.common.message.Message message, Object o) {
                return list.get(0);
            }
        });
        rocketMQTemplate.asyncSendOrderly(topic,msg,"",null);
    }

    /**
     * 单向发送
     * CommunicationMode: 3 种消息发送方式
     *  SYNC 同步 发送者向 MQ 执行发送消息API 时，同步等待，直到消息服务器返回发送结果,
     *  ASYNC 异步 发送者向MQ 执行发送消息API 时，指定消息发送成功后的回调函数，然后调用消息发送API 后，立即返回，消息发送者线程不阻塞，直到运行结束，消息发送成功或失败的回调任务在一个新的线程中返回,
     *  ONEWAY 单向 消息发送者向MQ 执行发送消息API 时，直接返回，不等待消息服务器的结果，也不注册回调函数，只管发，不管是否成功存储在消息服务器上;
     * @param topic
     * @param msg
     */
    public void sendOneWay(String topic,String msg){
        rocketMQTemplate.sendOneWay(topic,msg);
    }

    /**
     * 按需单向发送
     * @param topic
     * @param msg
     */
    public void sendOneWayOrderly(String topic,String msg){
        rocketMQTemplate.sendOneWayOrderly( topic, msg, "");
    }

    /**
     * 发送事务消息
     * 事务消息需要配置监听来监听本地事务提交情况
     * @param topic
     * @param msg
     */
    public void sendMessageInTransaction(String topic,String msg){
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            //构建 Message对象
            Message<String> message = MessageBuilder.withPayload(msg)
                    .setHeader(RocketMQHeaders.TRANSACTION_ID,"TransID_"+i)
                    //发到事务监听器里后，这个自己设定的TAGS属性会丢失。但是上面那个属性不会丢失。
                    .setHeader(RocketMQHeaders.TAGS,tags[i % tags.length])
                    //MyProp在事务监听器里也能拿到，为什么就单单这个RocketMQHeaders.TAGS拿不到？这只能去调源码了。
                    .setHeader("MyProp","MyProp_"+i)
                    .build();
            //这里发送事务消息时，还是会转换成RocketMQ的Message对象，再调用RocketMQ的API完成事务消息机制。
            SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(topic, message,topic);
            System.out.printf("%s%n", sendResult);
        }

    }

}

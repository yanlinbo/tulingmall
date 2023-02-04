package com.ylb.rocketmq.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.StringMessageConverter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 事务消息监听器
 */
@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplate")  //该注解表示监听rocketMQTemplate
public class MyTransactionListenerImpl implements RocketMQLocalTransactionListener {


    private ConcurrentHashMap<Object, Message> localTrans = new ConcurrentHashMap<>();@Override

    /**
     * 该方法用来执行本地事务
     */
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        //把事务id和消息进行绑定
        Object transId = msg.getHeaders().get(RocketMQHeaders.PREFIX+RocketMQHeaders.TRANSACTION_ID);
        localTrans.put(transId,msg);

        String destination = arg.toString();
        //转换为RocketMQ下的Message 这样就可以获取Tag了
        org.apache.rocketmq.common.message.Message message = RocketMQUtil.convertToRocketMessage(new StringMessageConverter(),"UTF-8",destination, msg);
        String tags = message.getTags();
        //判断对那些消息进行提交，对那些消息进行回滚，对那些消息进行未明
        if(StringUtils.contains(tags,"TagA")){
            return RocketMQLocalTransactionState.COMMIT;
        }else if(StringUtils.contains(tags,"TagB")){
            return RocketMQLocalTransactionState.ROLLBACK;
        }else{
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    /**
     * 本地事务的检查接口,即MQServer没有收到二次确认消息时调用的方法,去检查本地事务到底有没有成功
     * @param message
     * @return
     */

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {

        String transId = message.getHeaders().get(RocketMQHeaders.PREFIX+RocketMQHeaders.TRANSACTION_ID).toString();
        Message originalMessage = localTrans.get(transId);

        String tags = message.getHeaders().get(RocketMQHeaders.PREFIX+RocketMQHeaders.TAGS).toString();
        if(StringUtils.contains(tags,"TagC")){
            return RocketMQLocalTransactionState.COMMIT;
        }else if(StringUtils.contains(tags,"TagD")){
            return RocketMQLocalTransactionState.ROLLBACK;
        }else{
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}

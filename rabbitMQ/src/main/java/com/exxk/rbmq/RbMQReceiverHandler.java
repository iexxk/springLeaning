package com.exxk.rbmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 监听接收消息
 */
@Component
public class RbMQReceiverHandler implements MessageListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message) {
        log.info("====接收到" + message.getMessageProperties().getConsumerQueue() + "队列的消息=====");
        log.info(message.getMessageProperties().toString());
        log.info(new String(message.getBody()));
    }
}

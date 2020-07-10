package com.exxk.rbmq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(cn.hutool.extra.spring.SpringUtil.class)  //huTool添加，才能用getBean
public class RabbitMQConfig {

    @Autowired
    RbMQReceiverHandler rbMQReceiverHandler;

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("test1_staff");
        container.setMessageListener(rbMQReceiverHandler);
        return container;
    }
}

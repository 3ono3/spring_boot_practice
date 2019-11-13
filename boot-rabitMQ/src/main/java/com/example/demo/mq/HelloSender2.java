package com.example.demo.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author GuoJingyuan
 * @date 2019/9/20
 */
@Component
public class HelloSender2 {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        System.out.println("sender2" + i);
        this.rabbitTemplate.convertAndSend("hello", i);
    }
}

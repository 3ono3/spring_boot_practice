package com.example.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author GuoJingyuan
 * @date 2019/9/20
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {
    @RabbitHandler
    public void process(Integer i) {
        System.out.println("Receiver1: " + i);
    }

//    @RabbitHandler
//    public void process2(Integer i) {
//        System.out.println("Receiver2: " + i);
//    }
}

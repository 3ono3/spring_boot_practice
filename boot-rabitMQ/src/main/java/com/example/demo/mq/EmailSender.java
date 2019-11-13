package com.example.demo.mq;

import com.example.demo.entity.Email;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author GuoJingyuan
 * @date 2019/9/20
 */
@Component
public class EmailSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Email email) {
        System.out.println("sender");
        this.rabbitTemplate.convertAndSend("emailQue", email);
    }
}

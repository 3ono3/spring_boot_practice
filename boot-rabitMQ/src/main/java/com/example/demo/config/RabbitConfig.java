package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 队列配置
 * @author GuoJingyuan
 * @date 2019/9/20
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue simpleQueue(){
        return new Queue("hello");
    }

    @Bean
    public Queue emailQue() {
        return new Queue("emailQue");
    }

}

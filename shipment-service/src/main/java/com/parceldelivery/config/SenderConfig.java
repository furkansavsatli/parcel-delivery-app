package com.parceldelivery.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {

    @Bean
    public Queue queueDelete() {
        return new Queue("deliveryOrder-delete", true);
    }

    @Bean
    public Queue queue() {
        return new Queue("deliveryOrder", true);
    }
}
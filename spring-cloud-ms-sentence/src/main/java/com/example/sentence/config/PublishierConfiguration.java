package com.example.sentence.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublishierConfiguration {
    @Value("${app.queue.notify.name}")
    private String notifyQueueName;

    @Bean("queueNotify")
    Queue queueNotify() {
        return new Queue(notifyQueueName, true);
    }
}

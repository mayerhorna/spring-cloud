package com.example.sentence.publisher;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.sentence.domain.Sentence;

@Component
@EnableRabbit
public class NotifyPublisher {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	@Qualifier("queueNotify")
	private Queue queueNotify;
	/*
	public void sendNotify(Object message) {
		rabbitTemplate.convertAndSend(queueNotify.getName(), message);
	}
	*/
	public void sendNotify(Sentence sentence) {
		rabbitTemplate.convertAndSend(queueNotify.getName(), sentence);
	}
}

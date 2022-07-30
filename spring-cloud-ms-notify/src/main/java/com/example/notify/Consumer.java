package com.example.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.sentence.domain.Sentence;

@Component
public class Consumer {
	Logger logger = LoggerFactory.getLogger(Consumer.class);
	/*
	@RabbitListener(queues = {"${app.queue.name}"})
	public void received(@Payload String message) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Mensaje recibido: {}", message);
		sendEmail(message);
	}*/
	
	@RabbitListener(queues = {"${app.queue.name}"})
	public void received(@Payload Sentence sentence) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Mensaje recibido: {}", sentence.getText());
		sendEmail(sentence.getText());
	}
	
	private void sendEmail(String message) {
		logger.info("Inicio: envio email");
		logger.info("Enviando email");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Fin: envio email");	 
	}
}


package com.example.notify;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.notify.mappers.SentenceMapper;
import com.example.sentence.domain.Sentence;

@Component
public class Consumer {
	@Autowired
    private SentenceMapper sentenceMapper;
	 
	Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@RabbitListener(queues = {"${app.queue.name}"})
	public void received(@Payload Sentence sentence) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Mensaje recibido: {}", sentence.getText());
		sentence.setCreated(new Date());
		saveSentence(sentence);
		sendEmail(sentence.getText());
	} 
	
	private void saveSentence(Sentence sentence) {
		sentenceMapper.save(sentence);
		logger.info("SENTENCE ID GENERADO: {}", sentence.getId());
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


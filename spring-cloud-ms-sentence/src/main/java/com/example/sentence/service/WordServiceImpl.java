package com.example.sentence.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.sentence.dao.AdjectiveClient;
import com.example.sentence.dao.ArticleClient;
import com.example.sentence.dao.NounClient;
import com.example.sentence.dao.PronounClient;
import com.example.sentence.dao.VerbClient;
import com.example.sentence.domain.Word;

import feign.FeignException.InternalServerError;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;

@Service
public class WordServiceImpl implements WordService{
	
	@Autowired
	@Qualifier("cbConfig1")
	private io.github.resilience4j.circuitbreaker.CircuitBreaker cbConfig1;
	
	private Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);
	
	@PostConstruct
	public void init() {
		this.cbConfig1.getEventPublisher()
		.onSuccess(event -> 
			logger.info("Evento {}", event.getEventType())
		)
		.onError(event ->
			logger.error("Evento {}, Error {}", event.getEventType(), event.getThrowable().getMessage())
		) 
		.onCallNotPermitted(event -> 
			logger.warn("Evento {}, el circuito esta abierto", event.getEventType())
		)
		.onStateTransition(event -> 
			logger.warn("Evento {} pasó a {}", event.getEventType(), event.getStateTransition())
		);
	}

	
	@Autowired
	private PronounClient pronounClient;
	
	@Autowired
	private VerbClient verbClient;
	
	@Autowired
	private AdjectiveClient adjectiveClient;
	
	@Autowired
	private NounClient nounClient;
	
	@Autowired
	private ArticleClient articleClient;
	
	@Override
	public Word getPronoun() {
		return pronounClient.getWord();
	}

	@Override
	public Word getVerb() {
		 return Decorators.ofSupplier(() -> this.verbClient.getWord())
				 .withCircuitBreaker(this.cbConfig1)
				 .withFallback(Arrays.asList(RuntimeException.class), this::getFallbackVerb)
				 .get();
	}
	
	public Word getFallbackVerb(Throwable throwable) {
		Word word = new Word();
		word.setText("-");
		return word;
	}


	@Override
	@CircuitBreaker(name = "adjectiveCB", fallbackMethod = "getFallbackAdjective")
	public Word getAdjective() {
		return adjectiveClient.getWord();
	}
	
	public Word getFallbackAdjective(Exception ex) {
		Word wordEmpty = new Word();
		wordEmpty.setText("");
		return wordEmpty;
	}
	
	public Word getFallbackAdjective(InternalServerError ex) {
		Word wordEmpty = new Word();
		wordEmpty.setText("*");
		return wordEmpty;
	}

	@Override
	public Word getNoun() {
		return nounClient.getWord();
	}

	@Override
	public Word getArticle() {
		return articleClient.getWord();
	}
	
}

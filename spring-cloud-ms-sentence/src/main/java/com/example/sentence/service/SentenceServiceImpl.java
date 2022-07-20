package com.example.sentence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentenceServiceImpl implements SentenceService{

	@Autowired
	private WordService wordService;
	
	@Override
	public String buildSentence() {
		return wordService.getPronoun().getText() + 
				" " + wordService.getVerb().getText()
				+ " " + wordService.getArticle().getText()
				+ " " + wordService.getNoun().getText()
				+ " " + wordService.getAdjective().getText();
	}

}

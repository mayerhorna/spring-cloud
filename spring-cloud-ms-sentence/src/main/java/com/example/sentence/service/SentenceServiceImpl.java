package com.example.sentence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sentence.domain.Sentence;
import com.example.sentence.publisher.NotifyPublisher;

@Service
public class SentenceServiceImpl implements SentenceService{

	@Autowired
	private WordService wordService;
	
	@Autowired
	private NotifyPublisher notifyPublisher;
	
	@Override
	public String buildSentence() {
		String sentence = wordService.getPronoun().getText() + 
				" " + wordService.getVerb().getText()
				+ " " + wordService.getArticle().getText()
				+ " " + wordService.getNoun().getText()
				+ " " + wordService.getAdjective().getText();
		//notifyPublisher.sendNotify(sentence);
		Sentence sentenceObj = new Sentence();
		sentenceObj.setText(sentence);
		notifyPublisher.sendNotify(sentenceObj);
		
		return sentence;
	}

}

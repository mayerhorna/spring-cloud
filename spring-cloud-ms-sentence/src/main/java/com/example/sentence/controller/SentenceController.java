package com.example.sentence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sentence.domain.Sentence;
import com.example.sentence.service.SentenceService;

@RestController
@RequestMapping("/sentence")
public class SentenceController {

	@Autowired
	private SentenceService sentenceService;

	@GetMapping("/get")
	public @ResponseBody Sentence getSentence() {
		Sentence sentence = new Sentence();
		String sentenceText = sentenceService.buildSentence();
		sentence.setText(sentenceText);
		return sentence;
	}
	
	@GetMapping("/list")
	public @ResponseBody List<Sentence> getSentences() {
		Sentence sentence = new Sentence();
		String sentenceText = sentenceService.buildSentence();
		sentence.setText(sentenceText);
		return null;
	}
}

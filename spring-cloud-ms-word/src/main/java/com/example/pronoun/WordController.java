package com.example.pronoun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class WordController {

	@Value("${words}")
	private String words;

	@RequestMapping("/")
	public @ResponseBody Word getWord() {
		String[] wordArray = words.split(",");
		int i = (int) Math.round(Math.random() * (wordArray.length - 1));
		return new Word(wordArray[i]);
	}
 
	@RequestMapping("/saveWord")
	public @ResponseBody Word saveWord(Word word) {
		String[] wordArray = words.split(",");
		int i = (int) Math.round(Math.random() * (wordArray.length - 1));
		return new Word(wordArray[i]);
	}
	/*
	@RequestMapping("/updateWord")
	public @ResponseBody Word updateWord() {
		String[] wordArray = words.split(",");
		int i = (int) Math.round(Math.random() * (wordArray.length - 1));
		return new Word(wordArray[i]);
	}
	
	@RequestMapping("/getList")
	public @ResponseBody List<Word> getList() {
		String[] wordArray = words.split(",");
		int i = (int) Math.round(Math.random() * (wordArray.length - 1));
		return null;
	}
*/

}

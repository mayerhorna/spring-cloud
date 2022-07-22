package com.example.sentence.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.sentence.domain.Word;

@FeignClient("spring-cloud-ms-pronoun")
public interface PronounClient {
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public Word getWord();
	 
}

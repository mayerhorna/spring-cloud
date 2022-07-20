package com.example.sentence.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sentence.domain.Word;

@Service
public class WordServiceImpl implements WordService{

	@Autowired
	private LoadBalancerClient client;

	public Word getWord(String service) {
		ServiceInstance serviceInstance = client.choose(service);
		Word word = new Word();
		URI uri = serviceInstance.getUri();
		if (uri != null) {
			word = (new RestTemplate()).getForObject(uri, Word.class);
		}
		return word;
	}
	
	@Override
	public Word getPronoun() {
		return getWord("spring-cloud-ms-pronoun");
	}

	@Override
	public Word getVerb() {
		return getWord("spring-cloud-ms-verb");
	}

	@Override
	public Word getAdjective() {
		return getWord("spring-cloud-ms-adjective");
	}

	@Override
	public Word getNoun() {
		return getWord("spring-cloud-ms-noun");
	}

	@Override
	public Word getArticle() {
		return getWord("spring-cloud-ms-article");
	}
	
}

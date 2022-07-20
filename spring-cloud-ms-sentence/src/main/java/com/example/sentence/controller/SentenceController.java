package com.example.sentence.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.sentence.domain.Word;
import com.example.sentence.service.SentenceService;

@RestController
public class SentenceController {

	@Autowired
	private SentenceService sentenceService;
	

	@RequestMapping("/sentence")
	public @ResponseBody String getSentence() {
		return sentenceService.buildSentence();
	}
 
}

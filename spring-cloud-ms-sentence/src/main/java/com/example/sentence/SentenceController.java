package com.example.sentence;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SentenceController {

	@Autowired
	private DiscoveryClient client;

	@RequestMapping("/sentence")
	public @ResponseBody String getSentence() {
		return getWord("spring-cloud-ms-pronoun") + " " + getWord("spring-cloud-ms-verb") + " "
				+ getWord("spring-cloud-ms-article") + " " + getWord("spring-cloud-ms-noun") + " "
				+ getWord("spring-cloud-ms-adjective") + ".";
	}

	public String getWord(String service) {
		List<ServiceInstance> list = client.getInstances(service);
		String word = "";
		if (list != null && list.size() > 0) {
			URI uri = list.get(0).getUri();
			if (uri != null) {
				word = (new RestTemplate()).getForObject(uri, String.class);
			}
		}
		return word;
	}

}

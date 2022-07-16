package com.example.pronoun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudMsPronounApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMsPronounApplication.class, args);
	}

}

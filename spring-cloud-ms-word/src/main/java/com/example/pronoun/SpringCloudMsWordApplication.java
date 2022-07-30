package com.example.pronoun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudMsWordApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMsWordApplication.class, args);
	}

}

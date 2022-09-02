package com.example.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudOauthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudOauthServerApplication.class, args);
	}

}

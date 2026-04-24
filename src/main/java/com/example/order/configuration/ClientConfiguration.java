package com.example.order.configuration;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {
	@Bean
	HttpClient httpClient() {
		return HttpClient.newBuilder().version(Version.HTTP_2).connectTimeout(Duration.ofSeconds(10)).build();
	}

}

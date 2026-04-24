package com.example.order;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

@Component
public class HttpService {
	private final HttpClient httpClient;

	public HttpService(HttpClient httpClient) {

		this.httpClient = httpClient;
	}

	public CompletableFuture<String> getPostBystring(String s) {
		HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8082/inventory/getStock/" + s)).GET()
				.build();

		return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
	}
}

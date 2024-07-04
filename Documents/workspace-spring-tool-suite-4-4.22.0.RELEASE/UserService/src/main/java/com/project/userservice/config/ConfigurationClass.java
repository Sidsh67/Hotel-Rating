package com.project.userservice.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


import okhttp3.OkHttpClient;

@Configuration
public class ConfigurationClass {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    RestTemplate restTemplate = new RestTemplate();
	    HttpClient httpClient = HttpClientBuilder.create().build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	    restTemplate.setRequestFactory(requestFactory);
	    return restTemplate;
	}
	
	
	 @Bean
	 public OkHttpClient client() {
		 return new OkHttpClient();
	 } 
	 
}

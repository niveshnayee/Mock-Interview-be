package com.example.mock_interview_be.authentication.config;

import com.example.mock_interview_be.authentication.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private final JwtAuthFilter jwtAuthFilter;
    @Autowired
    public WebClientConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean(name = "authenticationWebClient")  // Give this bean a unique name
    public WebClient webClientBuilder() {
        return WebClient.builder().filter(jwtAuthFilter).build();  // Create WebClient from the builder
    }

}

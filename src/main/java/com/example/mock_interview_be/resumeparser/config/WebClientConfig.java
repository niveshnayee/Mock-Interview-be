//package com.example.mock_interview_be.resumeparser.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Configuration
//public class WebClientConfig {
//    @Primary
//    @Bean(name = "resumeParserWebClient")  // Explicitly name the bean differently
//    public WebClient webClient(WebClient.Builder builder){
//        return builder.build();
//    }
//
//}

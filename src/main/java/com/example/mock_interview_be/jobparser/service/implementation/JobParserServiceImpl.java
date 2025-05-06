package com.example.mock_interview_be.jobparser.service.implementation;

import com.example.mock_interview_be.jobparser.dto.StructuredJobDescriptionResponse;
import com.example.mock_interview_be.jobparser.service.interfaces.JobParserService;
import com.example.mock_interview_be.jobparser.util.JobValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class JobParserServiceImpl implements JobParserService {
    @Value("${job.extractor.url}")
    private String pythonApiUrl;
    private final WebClient webClient;

    @Autowired
    public JobParserServiceImpl(@Qualifier("authenticationWebClient") WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public Mono<StructuredJobDescriptionResponse> parseJobDescription(String description) {
        JobValidationUtil.validateJobDescription(description);
        Map<String, String> payload = new HashMap<>();
        payload.put("text", description);
        System.out.println("Parsing job description....");
        return webClient.post()
                .uri(pythonApiUrl)
                .header("Service-Name", "JobParsingService")  // Service name for dynamic token generation
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(StructuredJobDescriptionResponse.class)
                .doOnError(e -> System.out.println("WebClient Error: " + e.getMessage()));
    }
}

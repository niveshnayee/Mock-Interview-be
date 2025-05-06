package com.example.mock_interview_be.jobparser.controller;

import com.example.mock_interview_be.jobparser.dto.StructuredJobDescriptionResponse;
import com.example.mock_interview_be.jobparser.service.interfaces.JobParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/api/job_description")
public class JobDescriptionController {

    @Autowired
    private JobParserService jobParserService;

    @PostMapping(value = "/upload", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<StructuredJobDescriptionResponse>> uploadJob(@RequestBody String description){
        return jobParserService.parseJobDescription(description)
                .map(ResponseEntity::ok)
                .onErrorResume(WebClientRequestException.class, ex -> {
                    // Python service unreachable (e.g., ECONNREFUSED)
                    System.err.println("Python service is unreachable: " + ex.getMessage());
                    return Mono.error(new RuntimeException("Job parser service is currently unavailable. Please try again later."));
                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    // Python service returned an error response (e.g., 400, 500)
                    System.err.println("Python service returned error: " + ex.getMessage());
                    return Mono.error(new RuntimeException("Job parser service encountered an error. Please try again later."));
                })
                .onErrorResume(Exception.class, ex -> {
                    // Catch-all for unexpected issues
                    System.err.println("Unexpected error while calling Python service: " + ex.getMessage());
                    return Mono.error(new RuntimeException("Unexpected error during job parsing."));
                });
//                        .build());
    }
}

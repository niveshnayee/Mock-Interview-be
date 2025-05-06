package com.example.mock_interview_be.jobparser.service.interfaces;

import com.example.mock_interview_be.jobparser.dto.JobDescriptionData;
import com.example.mock_interview_be.jobparser.dto.StructuredJobDescriptionResponse;
import reactor.core.publisher.Mono;

public interface JobParserService {
    Mono<StructuredJobDescriptionResponse> parseJobDescription(String description);
}

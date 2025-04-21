package com.example.mock_interview_be.resumeparser.service.interfaces;

import com.example.mock_interview_be.resumeparser.dto.StructuredResumeResponse;
import io.micrometer.observation.ObservationFilter;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface ResumeParserService {
    public String extractText(MultipartFile file);

    public Mono<StructuredResumeResponse> extractResume(MultipartFile file);
}


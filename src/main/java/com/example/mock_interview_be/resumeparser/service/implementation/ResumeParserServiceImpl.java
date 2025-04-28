package com.example.mock_interview_be.resumeparser.service.implementation;

import com.example.mock_interview_be.resumeparser.dto.StructuredResumeResponse;
import com.example.mock_interview_be.resumeparser.exception.ResumeParseException;
import com.example.mock_interview_be.resumeparser.service.interfaces.ClamAVScannerService;
import com.example.mock_interview_be.resumeparser.service.interfaces.ResumeParserService;
import com.example.mock_interview_be.resumeparser.util.FileValidationUtil;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResumeParserServiceImpl implements ResumeParserService {

    private final Tika tika;
    private final WebClient webClient;
    private final ClamAVScannerService clamAVScannerService;

    @Value("${resume.extractor.url}")
    private String pythonApiUrl; //

    @Value("${resume.extractor.token}")
    private String authToken; //

    @Autowired
    public ResumeParserServiceImpl(Tika tika, @Qualifier("authenticationWebClient") WebClient webClient, ClamAVScannerService clamAVScannerService){
        this.clamAVScannerService = clamAVScannerService;
        this.tika = tika;
        this.webClient = webClient;
    }

    @Override
    public String extractText(MultipartFile file) {
        try{
            FileValidationUtil.validateResumeFile(file);
            clamAVScannerService.scanFile(file);

            // Step 1: Extract raw text from resume
            String extractedText = tika.parseToString(file.getInputStream());

            // Step 2: Clean and normalize text
            // remove carriage returns and tabs
            // convert newlines to spaces
            // collapse multiple spaces

            return extractedText
                    .replaceAll("[\\r\\t]+", " ")  // remove carriage returns and tabs
                    .replaceAll("[\\n]+", " ")     // convert newlines to spaces
                    .replaceAll(" +", " ")         // collapse multiple spaces
                    .trim();

        } catch (IOException | TikaException e) {
            throw new ResumeParseException("Failed to extract text from resume", e);
        }
    }

    @Override
    public Mono<StructuredResumeResponse> extractResume(MultipartFile file) {
        String cleanedText = extractText(file);
        Map<String, String> payload = new HashMap<>();
        payload.put("text", cleanedText);
        return webClient.post()
                .uri(pythonApiUrl)
                .header("Service-Name", "ResumeParsingService")  // Service name for dynamic token generation
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(StructuredResumeResponse.class)
                .doOnError(e -> System.out.println("WebClient Error: " + e.getMessage()));

    }
}

//package com.example.mock_interview_be.service;
//
//import com.example.mock_interview_be.resumeparser.dto.*;
//import com.example.mock_interview_be.resumeparser.service.implementation.ResumeParserServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class ResumeServiceTest {
//
//    @Mock
//    private WebClient.Builder webClientBuilder;  // Mock the WebClient.Builder
//    @Mock
//    private WebClient webClient;
//    @Mock
//    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
//    @Mock
//    private WebClient.RequestHeadersSpec requestHeadersSpec;
//    @Mock
//    private WebClient.RequestBodySpec requestBodySpec;  // Mock the body spec
//    @Mock
//    private WebClient.ResponseSpec responseSpec;  // Mock the response spec
//
//    @InjectMocks
//    private ResumeParserServiceImpl resumeService;  // The service you're testing
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);  // Mock base URL setting
//
//        // Mock the behavior of the WebClient to return a successful response
//        when(webClientBuilder.build()).thenReturn(webClient);
//        when(webClient.post()).thenReturn(requestBodyUriSpec);
//        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
//        when(requestBodySpec.contentType(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
//        when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
//        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//
//
//        // Mock the API response (the response from the Python service)
//        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
//        ResumeData resumeData = new ResumeData(
//                "John Doe",
//                "john.doe@example.com",
//                "123-456-7890",
//                "",
//                "",
//                List.of("Java", "Python"),
//                List.of(new Education("Bachelors", "XYZ University", "2020", "2024", "3.5")),
//                List.of(new Experience("XYZ Corp", "Software Engineer", "2022-01", "Present", 18, "Develop software")),
//                List.of("Cert1"),
//                List.of(new Project("Project1", "Description", List.of("Java"))),
//                List.of("English")
//        );
//        StructuredResumeResponse mockResponse = new StructuredResumeResponse("12345569fjbewjf", resumeData);
//        when(responseSpec.bodyToMono(StructuredResumeResponse.class))
//                .thenReturn(Mono.just(mockResponse));
//    }
//
//    @Test
//    public void testExtractResume() throws IOException {
//        MultipartFile mockFile = mock(MultipartFile.class);  // Create a mock MultipartFile
//        when(mockFile.getInputStream()).thenReturn(new ByteArrayInputStream("Test resume content".getBytes()));
//        when(mockFile.getOriginalFilename()).thenReturn("resume.pdf");
//
//        // Call the method you're testing
//        Mono<StructuredResumeResponse> responseMono = resumeService.extractResume(mockFile);
//
//        // Verify the response from the mocked Python service
//        StructuredResumeResponse response = responseMono.block();
//        assertNotNull(response);
//        assertEquals("John Doe", response.getFull_name());
//        assertEquals("john.doe@example.com", response.getEmail());
//    }
//
//}

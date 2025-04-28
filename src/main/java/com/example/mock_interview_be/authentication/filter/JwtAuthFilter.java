package com.example.mock_interview_be.authentication.filter;

import com.example.mock_interview_be.authentication.config.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements ExchangeFilterFunction {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtAuthFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        String serviceName = request.headers().getFirst("Service-Name");

        if (serviceName == null) {
            logger.error("Missing 'Service-Name' header. URL: {}. Headers: {}", request.url(), request.headers());
            return Mono.error(new IllegalArgumentException("The 'Service-Name' header is required and cannot be null."));
        }

        String token;
        try {
            token = jwtTokenUtil.generateToken(serviceName);
        } catch (Exception e) {
            logger.error("Failed to generate JWT token for service: {}", serviceName, e);
            return Mono.error(new RuntimeException("Failed to generate JWT token"));
        }

        ClientRequest modifiedRequest = ClientRequest.from(request)
                .header("Authorization", "Bearer " + token)
                .header("Service-Name", serviceName)  // Add the service name header
                .build();
        return next.exchange(modifiedRequest);
    }
}

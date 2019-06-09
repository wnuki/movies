package com.movies.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
public class CoreConfiguration implements WebMvcConfigurer {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .errorHandler(new ClientErrorHandler())
                .build();
    }

    private class ClientErrorHandler implements ResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
            return !HttpStatus.OK.equals(httpResponse.getStatusCode());
        }
        @Override
        public void handleError(ClientHttpResponse httpResponse) throws IOException {
            throw new RuntimeException("Error while making request");
        }
    }
}

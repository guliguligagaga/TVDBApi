package com.denisKhalizov.config;

import com.denisKhalizov.dto.JwtRequest;
import com.denisKhalizov.service.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Autowired
    RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;
    @Value("${jwt.user}")
    private String user;
    @Value("${jwt.userKey}")
    private String userKey;
    @Value("${jwt.apiKey}")
    private String secret;

    @Bean
    public JwtRequest generateJwtRequest() {
        return new JwtRequest(user, userKey, secret);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(restTemplateResponseErrorHandler);
        return restTemplate;
    }

}


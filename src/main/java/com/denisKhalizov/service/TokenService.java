package com.denisKhalizov.service;

import com.denisKhalizov.dto.JwtRequest;
import com.denisKhalizov.dto.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {

    final RestTemplate restTemplate;
    final JwtRequest jwtRequest;

    @Value("${url.login}")
    private String tokenUrl;

    @Autowired
    public TokenService(RestTemplate restTemplate, JwtRequest jwtRequest) {
        this.restTemplate = restTemplate;
        this.jwtRequest = jwtRequest;
    }

    public HttpEntity<String> getEntityWithHeaderToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getToken());
        return new HttpEntity<>(headers);
    }

    private String getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JwtRequest> entity = new HttpEntity<>(jwtRequest, headers);
        ResponseEntity<JwtResponse> stringResponseEntity = restTemplate.postForEntity(tokenUrl, entity, JwtResponse.class);
        return stringResponseEntity.getBody().getToken();
    }
}

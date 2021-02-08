package com.example.denisKhalizov;

import com.denisKhalizov.dto.JwtResponse;
import com.denisKhalizov.service.TokenService;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TokenTest extends AbstractTest {

    @Autowired
    TokenService tokenService;

    @Test
    public void getToken() {
        JwtResponse jwtResponse = JwtResponse.builder().token("testToken").build();
        ResponseEntity<JwtResponse> myEntity = new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        when(restTemplate.postForEntity(
                eq(tokenUrl),
                any(),
                eq(JwtResponse.class))
        ).thenReturn(myEntity);
        assertEquals("Bearer testToken", tokenService.getEntityWithHeaderToken()
                .getHeaders().get("Authorization").get(0));
    }

}

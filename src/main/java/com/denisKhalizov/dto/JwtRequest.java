package com.denisKhalizov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class JwtRequest {

    private String user;
    private String userKey;
    private String apiKey;
}

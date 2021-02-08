package com.denisKhalizov.starters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.denisKhalizov")
public class DenisKhalizovApplication {

    public static void main(String[] args) {
        SpringApplication.run(DenisKhalizovApplication.class, args);
    }

}

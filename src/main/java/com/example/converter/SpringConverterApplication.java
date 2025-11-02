package com.example.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringConverterApplication {
    public static void main(String[] args) {
        // Ensure Spring loads properties.yml as the config file (single source)
        System.setProperty("spring.config.name", "properties");
        SpringApplication.run(SpringConverterApplication.class, args);
    }
}

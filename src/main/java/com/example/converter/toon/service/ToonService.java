package com.example.converter.toon.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ToonService {

    private final WebClient webClient;

    public ToonService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:4000").build();
    }

    // JSON → TOON
    public String jsonToToon(String json) {
        return webClient.post()
                .uri("/convert/json-to-toon")
                .header("Content-Type", "application/json")
                .bodyValue(json)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // TOON → JSON
    public String toonToJson(String toon) {
        String requestBody = String.format("{\"toon\": \"%s\"}", toon.replace("\"", "\\\"").replace("\n", "\\n"));
        return webClient.post()
                .uri("/convert/toon-to-json")
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

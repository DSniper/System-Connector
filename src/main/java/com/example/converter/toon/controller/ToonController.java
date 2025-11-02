package com.example.converter.toon.controller;

import com.example.converter.toon.service.ToonService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/toon")
public class ToonController {

    private final ToonService toonService;

    public ToonController(ToonService toonService) {
        this.toonService = toonService;
    }

    @PostMapping("/json-to-toon")
    public ResponseEntity<?> convertJsonToToon(@RequestBody Map<String, Object> jsonBody) {
        try {
            String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(jsonBody);
            String toonData = toonService.jsonToToon(json);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "toon", toonData
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @PostMapping("/toon-to-json")
    public ResponseEntity<?> convertToonToJson(@RequestBody Map<String, Object> body) {
        try {
            String toon = (String) body.get("toon");
            String jsonData = toonService.toonToJson(toon);

            // Convert JSON string to an actual Map
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            Map<String, Object> parsedJson = mapper.readValue(jsonData, Map.class);

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "json", parsedJson
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

}

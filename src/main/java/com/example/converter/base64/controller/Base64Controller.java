package com.example.converter.base64.controller;

import com.example.converter.base64.service.Base64Service;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/base64", produces = MediaType.APPLICATION_JSON_VALUE)
public class Base64Controller {
    private final Base64Service base64Service;

    public Base64Controller(Base64Service base64Service) {
        this.base64Service = base64Service;
    }

    @PostMapping(path = "/encode-text", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> encodeText(@RequestBody Map<String, String> body) {
        String text = body.get("text");
        if (text == null) {
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", "Missing 'text' in body");
            return ResponseEntity.badRequest().body(err);
        }
        String b64 = base64Service.encodeText(text);
        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "success");
        resp.put("message", "Encoded text to Base64");
        resp.put("data", b64);
        return ResponseEntity.ok(resp);
    }

    @PostMapping(path = "/decode-text", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> decodeText(@RequestBody Map<String, String> body) {
        String b64 = body.get("base64");
        if (b64 == null) {
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", "Missing 'base64' in body");
            return ResponseEntity.badRequest().body(err);
        }
        String text = base64Service.decodeToText(b64);
        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "success");
        resp.put("message", "Decoded Base64 to text");
        resp.put("data", text);
        return ResponseEntity.ok(resp);
    }

    @PostMapping(path = "/encode-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> encodeFile(@RequestPart("file") MultipartFile file) {
        try {
            String b64 = base64Service.encodeFile(file);
            Map<String, Object> resp = new HashMap<>();
            resp.put("status", "success");
            resp.put("message", "Encoded file to Base64");
            resp.put("filename", file.getOriginalFilename());
            resp.put("data", b64);
            return ResponseEntity.ok(resp);
        } catch (IOException e) {
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", e.getMessage());
            return ResponseEntity.status(500).body(err);
        }
    }

    @PostMapping(path = "/decode-file", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> decodeFile(@RequestBody Map<String, String> body) {
        String b64 = body.get("base64");
        String filename = body.getOrDefault("filename", "decoded.bin");
        if (b64 == null) {
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", "Missing 'base64' in body");
            return ResponseEntity.badRequest().body(err);
        }
        byte[] data = base64Service.decodeFile(b64);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentLength(data.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}

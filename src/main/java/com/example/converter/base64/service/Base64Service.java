package com.example.converter.base64.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class Base64Service {
    public String encodeText(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public String decodeToText(String base64) {
        byte[] decoded = Base64.getDecoder().decode(base64);
        return new String(decoded);
    }

    public String encodeFile(MultipartFile file) throws IOException {
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] decodeFile(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}

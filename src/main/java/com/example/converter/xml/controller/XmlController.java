package com.example.converter.xml.controller;

import com.example.converter.xml.service.XmlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/xml", produces = MediaType.APPLICATION_JSON_VALUE)
public class XmlController {

    private final XmlService xmlService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public XmlController(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @PostMapping(path = "/convert-to-json", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> convertXmlToJson(@RequestBody String xml) {
        try {
            String json = xmlService.xmlToJson(xml);
            Map<String, Object> resp = new HashMap<>();
            resp.put("status", "success");
            resp.put("message", "Converted XML to JSON");
            // Parse the JSON string to a real JSON object
            resp.put("data", objectMapper.readTree(json));
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(err);
        }
    }

    @PostMapping(path = "/convert-to-xml", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> convertJsonToXml(@RequestBody String json) {
        try {
            String xml = xmlService.jsonToXml(json);
            Map<String, Object> resp = new HashMap<>();
            resp.put("status", "success");
            resp.put("message", "Converted JSON to XML");
            resp.put("data", xml);
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(err);
        }
    }
}

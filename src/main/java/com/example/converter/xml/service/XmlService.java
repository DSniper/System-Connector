package com.example.converter.xml.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class XmlService {
    private final XmlMapper xmlMapper;
    private final ObjectMapper jsonMapper;

    public XmlService(XmlMapper xmlMapper, ObjectMapper jsonMapper) {
        this.xmlMapper = xmlMapper;
        this.jsonMapper = jsonMapper;
    }

    public String xmlToJson(String xml) throws IOException {
        JsonNode node = xmlMapper.readTree(xml.getBytes());
        return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

    public String jsonToXml(String json) throws JsonProcessingException {
        JsonNode node = jsonMapper.readTree(json);
        return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }
}

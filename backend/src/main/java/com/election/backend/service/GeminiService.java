package com.election.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    @SuppressWarnings("unchecked")
    public String generateResponse(List<Map<String, String>> messages) {
        if (apiKey == null || apiKey.isEmpty() || apiKey.equals("YOUR_API_KEY")) {
            return "API Key is not configured. Please set GEMINI_API_KEY environment variable.";
        }

        String url = GEMINI_URL + apiKey;

        List<Map<String, Object>> contents = new ArrayList<>();
        
        for (Map<String, String> msg : messages) {
            String role = msg.get("role").equals("assistant") ? "model" : "user";
            String text = msg.get("text");
            
            Map<String, Object> part = Map.of("text", text);
            contents.add(Map.of(
                "role", role,
                "parts", Collections.singletonList(part)
            ));
        }

        Map<String, Object> systemInstruction = Map.of(
            "parts", Collections.singletonList(Map.of("text", "You are an expert assistant for the Election Commission of India. Provide accurate, clear, and concise answers about the Indian election process. Use Markdown to format your response nicely with bolding and lists if applicable."))
        );

        Map<String, Object> requestBody = Map.of(
            "system_instruction", systemInstruction,
            "contents", contents
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            Map<String, Object> body = response.getBody();

            if (body != null && body.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) body.get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> contentBlock = (Map<String, Object>) candidates.get(0).get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) contentBlock.get("parts");
                    if (!parts.isEmpty()) {
                        return (String) parts.get(0).get("text");
                    }
                }
            }
            return "Sorry, I couldn't generate a response at this time.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error communicating with the AI service: " + e.getMessage();
        }
    }
}

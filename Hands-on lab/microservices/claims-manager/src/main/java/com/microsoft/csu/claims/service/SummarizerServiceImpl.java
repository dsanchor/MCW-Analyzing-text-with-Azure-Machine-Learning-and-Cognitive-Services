package com.microsoft.csu.claims.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.microsoft.csu.claims.config.ConfigProperties;

@Service
public class SummarizerServiceImpl implements SummarizerService {
    
    @Autowired
    public ConfigProperties config;

    
    @Override
    public String getSummary(String claim) {
        WebClient client = buildClient(config.getSummarizer().getUrl());


        System.out.println("Sending body: " + claim);

        ResponseSpec responseSpec = buildResponseSpec(client, 
            config.getSummarizer().getPath(), 
            config.getSummarizer().getKey(), claim);

        String response = responseSpec.bodyToMono(String.class).block();

        System.out.println("Response from summarizer service:" + response);

        ObjectMapper mapper = new ObjectMapper();
        String summary = "";
        try {
            String[] items = mapper.readValue(response, String[].class);
            StringBuilder strBuilder = new StringBuilder();
            for (String item : items) {
                strBuilder.append(item).append(" ");
            }
            summary = strBuilder.toString().trim();
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing response");
        }

        return summary;
    }

    private ResponseSpec buildResponseSpec(WebClient client, String path, String key, String body) {
       return  client.post()
        .uri(path)
        .bodyValue(body)
        .header(
            HttpHeaders.AUTHORIZATION, "Bearer " + key)
        .accept(MediaType.APPLICATION_JSON)
        .acceptCharset(StandardCharsets.UTF_8)
        .retrieve();
    }

    private WebClient buildClient(String url) {
        WebClient client = WebClient.builder()
            .baseUrl(url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
            .build();

        return client;
    }
}

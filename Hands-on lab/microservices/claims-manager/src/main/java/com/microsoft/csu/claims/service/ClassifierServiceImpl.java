package com.microsoft.csu.claims.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.microsoft.csu.claims.config.ConfigProperties;

@Service
public class ClassifierServiceImpl implements ClassifierService {

    @Autowired
    private ConfigProperties config;

    @Override
    public ClaimType getClassifier(String claim) {

        WebClient client = buildClient(config.getClassifier().getUrl());

        String body = buildBody(claim);
        System.out.println("Sending body: " + body);

        ResponseSpec responseSpec = buildResponseSpec(client, 
            config.getClassifier().getPath(), 
            config.getClassifier().getKey(), body);

        String response = responseSpec.bodyToMono(String.class).block();

        System.out.println("Response from classifier service:" + response);

        ClaimType ctResponse = ClaimType.UNSPECIFIED;
        if (response!=null && !"".equals(response)) {
            if ("1".equals(response)) {
                ctResponse = ClaimType.CAR;
            } else if ("0".equals(response)) {
                ctResponse = ClaimType.HOME;
            }
        }
        return ctResponse;
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

    private String buildBody(String claim) {
        return new StringBuilder("[")
            .append("\"")
            .append(claim)
            .append("\"")
            .append("]")
            .toString();
    }

    private WebClient buildClient(String url) {
        WebClient client = WebClient.builder()
            .baseUrl(url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
            .build();

        return client;
    }
}

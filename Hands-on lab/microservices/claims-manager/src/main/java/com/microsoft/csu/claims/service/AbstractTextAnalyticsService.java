package com.microsoft.csu.claims.service;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.microsoft.csu.claims.config.ConfigProperties;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTextAnalyticsService {

    @Autowired
    private ConfigProperties config;

    protected TextAnalyticsClient getClient() {

        return new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(config.getTextAnalytics().getKey()))
                .endpoint(config.getTextAnalytics().getEndpoint())
                .buildClient();

    }
    
}

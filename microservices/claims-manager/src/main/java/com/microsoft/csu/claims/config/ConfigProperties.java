package com.microsoft.csu.claims.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="manager")
public class ConfigProperties {
    
    private Classifier classifier;

    private Summarizer summarizer;

    private TextAnalytics textAnalytics;

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public Summarizer getSummarizer() {
        return summarizer;
    }

    public void setSummarizer(Summarizer summarizer) {
        this.summarizer = summarizer;
    }

    public TextAnalytics getTextAnalytics() {
        return textAnalytics;
    }

    public void setTextAnalytics(TextAnalytics textAnalytics) {
        this.textAnalytics = textAnalytics;
    }   
    
}

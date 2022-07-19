package com.microsoft.csu.claims.service;

import com.microsoft.csu.claims.model.Sentiment;

public interface SentimentAnalizerService {

    public Sentiment getSentiment(String claim);
    
}

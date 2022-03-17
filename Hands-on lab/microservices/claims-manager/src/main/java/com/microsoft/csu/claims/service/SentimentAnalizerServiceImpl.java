package com.microsoft.csu.claims.service;

import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.microsoft.csu.claims.model.Sentiment;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalizerServiceImpl extends AbstractTextAnalyticsService  implements SentimentAnalizerService {

    @Override
    public Sentiment getSentiment(String claim) {
        
        DocumentSentiment documentSentiment = getClient().analyzeSentiment(claim);
        
        return Sentiment.fromDocument(documentSentiment);
    }
    
}

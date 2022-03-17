package com.microsoft.csu.claims.model;

import java.util.ArrayList;
import java.util.List;

import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.azure.ai.textanalytics.models.SentenceSentiment;

public class Sentiment {

    private String overallScore;

    private List<SentenceScore> sentencesScore;

    public Sentiment(){};

    public Sentiment(String overallScore, List<SentenceScore> sentencesScore) {
        this.overallScore = overallScore;
        this.sentencesScore = sentencesScore;
    }

    public static Sentiment fromDocument(DocumentSentiment ds) {
        Sentiment sentiment = new Sentiment();
        sentiment.setOverallScore(ds.getSentiment().toString().toLowerCase());
        List<SentenceScore> sentencesScores = new ArrayList<SentenceScore>();
        for (SentenceSentiment ss : ds.getSentences()) {
            SentenceScore sc = 
                new SentenceScore(ss.getText(), 
                    ss.getSentiment().toString().toLowerCase());
            sentencesScores.add(sc);
        }
        sentiment.setSentencesScore(sentencesScores);
        return sentiment;
    }

    public String getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(String overallScore) {
        this.overallScore = overallScore;
    }

    public List<SentenceScore> getSentencesScore() {
        return sentencesScore;
    }

    public void setSentencesScore(List<SentenceScore> sentencesScore) {
        this.sentencesScore = sentencesScore;
    } 

    
    
}

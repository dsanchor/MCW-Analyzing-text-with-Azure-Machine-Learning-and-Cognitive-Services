package com.microsoft.csu.claims.model;

import java.util.List;

public class Sentiment {

    private String overallScore;

    private List<SentenceScore> sentencesScore;

    public Sentiment(){};

    public Sentiment(String overallScore, List<SentenceScore> sentencesScore) {
        this.overallScore = overallScore;
        this.sentencesScore = sentencesScore;
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

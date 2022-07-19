package com.microsoft.csu.claims.model;

import java.util.List;

public class Sentiment {

    private String overallScore;

    private double neutralScore;

    private double negativeScore;

    private double positiveScore;

    private List<SentenceScore> sentencesScore;

    public Sentiment(){};

    public Sentiment(String overallScore, double neutralScore, double negativeScore, double positiveScore,
            List<SentenceScore> sentencesScore) {
        this.overallScore = overallScore;
        this.neutralScore = neutralScore;
        this.negativeScore = negativeScore;
        this.positiveScore = positiveScore;
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

    public double getNeutralScore() {
        return neutralScore;
    }

    public void setNeutralScore(double neutralScore) {
        this.neutralScore = neutralScore;
    }

    public double getNegativeScore() {
        return negativeScore;
    }

    public void setNegativeScore(double negativeScore) {
        this.negativeScore = negativeScore;
    }

    public double getPositiveScore() {
        return positiveScore;
    }

    public void setPositiveScore(double positiveScore) {
        this.positiveScore = positiveScore;
    } 
    
}

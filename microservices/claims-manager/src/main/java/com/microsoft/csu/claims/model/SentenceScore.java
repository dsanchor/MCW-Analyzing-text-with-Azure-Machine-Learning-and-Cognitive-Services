package com.microsoft.csu.claims.model;

public class SentenceScore {

    private String sentence;

    private String score;

    public SentenceScore(){};
    
    public SentenceScore(String sentence, String score) {
        this.sentence = sentence;
        this.score = score;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    
}

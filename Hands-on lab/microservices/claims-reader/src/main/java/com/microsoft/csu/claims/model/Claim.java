package com.microsoft.csu.claims.model;

import com.azure.spring.data.cosmos.core.mapping.Container;

import org.springframework.data.annotation.Id;

@Container(containerName = "Claims")
public class Claim {

    @Id
    private String id;

    private String claim;

    private String claimSummary;

    private String claimType;

    private Sentiment sentiment;

    private String [] keyPhrases;

    public Claim(){
        
    }

    public Claim(String id, String claim, String claimSummary, String claimType, Sentiment sentiment,
            String[] keyPhrases) {
        this.id = id;
        this.claim = claim;
        this.claimSummary = claimSummary;
        this.claimType = claimType;
        this.sentiment = sentiment;
        this.keyPhrases = keyPhrases;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public String getClaimSummary() {
        return claimSummary;
    }

    public void setClaimSummary(String claimSummary) {
        this.claimSummary = claimSummary;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Sentiment getSentiment() {
        return sentiment;
    }

    public void setSentiment(Sentiment sentiment) {
        this.sentiment = sentiment;
    }

    public String[] getKeyPhrases() {
        return keyPhrases;
    }

    public void setKeyPhrases(String[] keyPhrases) {
        this.keyPhrases = keyPhrases;
    }
 


}

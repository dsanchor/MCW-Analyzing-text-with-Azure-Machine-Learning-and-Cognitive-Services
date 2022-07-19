package com.microsoft.csu.claims.service;

public interface ClassifierService {
    

    public enum ClaimType {CAR, HOME, UNSPECIFIED};

    public ClaimType getClassifier(String claim);
}

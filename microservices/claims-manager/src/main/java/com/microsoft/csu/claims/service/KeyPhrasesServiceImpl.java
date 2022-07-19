package com.microsoft.csu.claims.service;

import com.azure.ai.textanalytics.models.KeyPhrasesCollection;

import org.springframework.stereotype.Service;

@Service
public class KeyPhrasesServiceImpl extends AbstractTextAnalyticsService implements KeyPhrasesService {

    @Override
    public String[] getKeyPhrases(String claim) {
        KeyPhrasesCollection kpc = getClient().extractKeyPhrases(claim);

        return kpc.stream().toArray(String[]::new);
    }
    
}

package com.microsoft.csu.claims.service;

import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.csu.claims.model.Sentiment;
import com.microsoft.csu.claims.repository.ClaimsRepository;

import com.microsoft.csu.claims.model.Claim;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimsRepository repository;

    @Autowired
    private ClassifierService classifierService;

    @Autowired
    private SummarizerService summarizerService;

    @Autowired
    private SentimentAnalizerService sentimentAnalizerService;

    @Autowired
    private KeyPhrasesService keyphrasesService;

    @Override
    public Claim createClaim(String claim) {
        return buildClaim(claim);
    }
    

    private Claim buildClaim(String inputClaim) {

        String id = UUID.randomUUID().toString();
        ClassifierService.ClaimType claimType = classifierService.getClassifier(inputClaim);
        String summary = summarizerService.getSummary(inputClaim);
        Sentiment sentiment = sentimentAnalizerService.getSentiment(inputClaim);
        String[] keyphrases = keyphrasesService.getKeyPhrases(inputClaim);

        Claim claim = new Claim(id, inputClaim, summary , claimType.toString().toLowerCase(), 
            sentiment, keyphrases, new Timestamp(System.currentTimeMillis()));

        final Mono<Claim> saveUserMono = repository.save(claim);

        return saveUserMono.block();
    }
}

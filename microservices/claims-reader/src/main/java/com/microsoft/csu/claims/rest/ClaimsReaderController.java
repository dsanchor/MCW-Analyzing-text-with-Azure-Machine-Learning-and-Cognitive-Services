package com.microsoft.csu.claims.rest;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import java.security.Identity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.microsoft.csu.claims.model.Claim;
import com.microsoft.csu.claims.model.SentenceScore;
import com.microsoft.csu.claims.model.Sentiment;
import com.microsoft.csu.claims.repository.ClaimsRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/claims")
public class ClaimsReaderController {

    @Autowired
    private ClaimsRepository repository;
    
    @GetMapping(produces = "application/json")
    public List<Claim> get(@RequestParam(required=false) String claimType) {

        Flux<Claim> claimsFlux = null;

        if (claimType!=null && !"".equals(claimType)) {
           claimsFlux = repository.findByClaimType(claimType);
        } else {
           claimsFlux = repository.findAll();
        }

        return claimsFlux.collectList().block();
    }

    @GetMapping(value="{id}", produces = "application/json")
    public ResponseEntity<Claim> getById(@PathVariable String id) {

        final Mono<Claim> claimMono = repository.findById(id);

        final Claim claim = claimMono.block();

        if (claim!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(claim);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
}

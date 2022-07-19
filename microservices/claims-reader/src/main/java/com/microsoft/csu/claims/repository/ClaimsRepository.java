package com.microsoft.csu.claims.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.microsoft.csu.claims.model.Claim;

import reactor.core.publisher.Flux;

import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepository extends ReactiveCosmosRepository<Claim, String> {
    
    Flux<Claim> findByClaimType(String claimType);

}

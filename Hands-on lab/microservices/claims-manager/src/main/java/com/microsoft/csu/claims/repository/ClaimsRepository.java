package com.microsoft.csu.claims.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.microsoft.csu.claims.model.Claim;

import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepository extends ReactiveCosmosRepository<Claim, String> {
    
}

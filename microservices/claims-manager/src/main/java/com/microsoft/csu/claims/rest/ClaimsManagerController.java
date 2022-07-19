package com.microsoft.csu.claims.rest;

import org.springframework.web.bind.annotation.RestController;

import com.microsoft.csu.claims.model.Claim;
import com.microsoft.csu.claims.service.ClaimService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/claims")
public class ClaimsManagerController {

    @Autowired
    ClaimService claimService;
    
    @PostMapping(produces = "application/json")
    public Claim create(@RequestBody Claim claim) {
        return claimService.createClaim(claim.getClaim());
    }

    
}

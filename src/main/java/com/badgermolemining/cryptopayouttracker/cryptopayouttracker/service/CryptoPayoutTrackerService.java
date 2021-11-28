package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;

import org.springframework.http.ResponseEntity;

public interface CryptoPayoutTrackerService {
    
    ResponseEntity<CoinGeckoPingResponse> sendPing();
}

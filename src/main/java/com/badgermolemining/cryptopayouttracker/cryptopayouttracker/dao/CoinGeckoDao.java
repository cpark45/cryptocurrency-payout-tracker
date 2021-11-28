package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;

import org.springframework.http.ResponseEntity;

public interface CoinGeckoDao {
    public ResponseEntity<CoinGeckoPingResponse> sendPing();
}
